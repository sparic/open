package cn.muye.cooperation.api.controller;

import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.api.service.IsvApplyService;
import cn.muye.core.AjaxResult;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.api.service.AgentApplyService;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.user.domain.User;
import cn.muye.user.api.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/9.
 */
@Controller
public class CooperationController {

    @Autowired
    private AgentApplyService agentApplyService;

    @Autowired
    private IsvApplyService isvApplyService;

    @Autowired
    private UserService userService;

    private static final int STATUS_SUBMIT = 0; //已提交
    private static final int STATUS_AUDITING = 1; //待审核
    private static final int STATUS_SUCCESS = 2; //成功
    private static final int STATUS_FAILED = 3; //失败

    /*
    * 修改代理商申请
    * */
    @RequestMapping(value = "/agentApply", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("agentApply:update")
    @ApiOperation(value = "更新代理商申请", httpMethod = "POST", notes = "更新代理商申请")
    public AjaxResult updateAgentApply(@RequestBody AgentApply agentApply) {
        if (agentApply == null || agentApply.getUserId() == null || agentApply.getUrl() == null) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "代理商申请信息有误，请检查后再提交");
        }
        AgentApply agentApplyDb = agentApplyService.getByUserId(agentApply.getUserId());
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        User userDb = userService.getUserById(agentApplyDb.getUserId());
        if (userDb != null) {
            if (!userDb.getUserName().equals(userName)) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您无权更改他人的代理商申请进展");
            }
        } else {
            AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在的用户");
        }
        if (agentApplyDb != null) {
            agentApplyDb.setUrl(agentApply.getUrl());
            agentApplyDb.setUpdateTime(new Date());
            agentApplyDb.setDescription(agentApply.getDescription());
            agentApplyDb.setStatus(ApplyStatusType.SUBMIT.getValue());
            agentApplyService.update(agentApplyDb);
            List<Map> result = Lists.newArrayList();
            result = assembleAgentApplyResult(result, agentApplyDb);
            return AjaxResult.success(result, "提交成功");
        } else {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在的代理商申请");
        }
    }

    /*
    * 新增或修改Isv申请
    * */
    @RequestMapping(value = "/isvApply", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("isvApply:upsert")
    @ApiOperation(value = "新增或更新ISV申请", httpMethod = "POST", notes = "新增或更新ISV申请")
    public AjaxResult addOrUpdateIsvApply(@RequestBody IsvApply isvApply) {
        if (isvApply == null || isvApply.getUserId() == null || isvApply.getUrl() == null) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "ISV申请信息有误，请检查后再提交");
        }
        Long userId = isvApply.getUserId();
        User userDb = userService.getUserById(userId);
        if (userDb.getLevel() == null) {
            return AjaxResult.failed(AjaxResult.CODE_NOT_AUTHORIZED_FAILED,"您不是代理商，无法申请ISV");
        }
        IsvApply isvApplyDb = isvApplyService.getByUserId(isvApply.getUserId());
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        if (userDb != null) {
            if (!userDb.getUserName().equals(userName)) {
                return AjaxResult.failed(AjaxResult.CODE_NOT_AUTHORIZED_FAILED, "您无权更改他人的ISV申请进展");
            } else if (userDb.getLevel() == null){
                return AjaxResult.failed(AjaxResult.CODE_NOT_AUTHORIZED_FAILED, "您不是代理商无权申请ISV");
            }
        } else {
            AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在的用户");
        }
        List<Map> result = Lists.newArrayList();
        if (isvApplyDb != null) {
            isvApplyDb.setUrl(isvApply.getUrl());
            isvApplyDb.setUpdateTime(new Date());
            isvApplyDb.setDescription(isvApply.getDescription());
            isvApplyDb.setStatus(ApplyStatusType.SUBMIT.getValue());
            isvApplyService.update(isvApplyDb);
            result = assembleIsvApplyResult(result, isvApplyDb);
            return AjaxResult.success(result, "提交成功");
        } else {
            isvApply.setCreateTime(new Date());
            isvApply.setStatus(ApplyStatusType.SUBMIT.getValue());
            isvApplyService.save(isvApply);
            assembleIsvApplyResult(result, isvApply);
            return AjaxResult.success(result, "申请成功");
        }
    }

    /**
     * 组装代理商进展的返回数据
     *
     * @param result
     * @param agentApply
     * @return
     */
    private List<Map> assembleAgentApplyResult(List<Map> result, AgentApply agentApply) {
        //遍历3次总共分3个状态(1. 已提交 2. 待审核 3. 通过/失败)
        for (int i = 0; i < 3; i++) {
            Map map = Maps.newHashMap();
            //给前端用，1代表正处于该阶段，0则反之
            map.put("flag", (i == Integer.valueOf(agentApply.getStatus()) ? "1" : "0"));
            map.put("description", (i == Integer.valueOf(agentApply.getStatus()) ? agentApply.getDescription() != null ? agentApply.getDescription() : "" : ""));
            //如果遍历到了通过/失败
            if (i == 2) {
                if (STATUS_SUCCESS == Integer.valueOf(agentApply.getStatus()) || STATUS_FAILED == Integer.valueOf(agentApply.getStatus())) {
                    map.put("status", STATUS_SUCCESS != Integer.valueOf(agentApply.getStatus()) && STATUS_FAILED != Integer.valueOf(agentApply.getStatus()) ? "" : agentApply.getStatus());
                }
            }
            result.add(map);
        }
        return result;
    }

    /**
     * 组装ISV进展的返回数据
     *
     * @param result
     * @param isvApply
     * @return
     */
    private List<Map> assembleIsvApplyResult(List<Map> result, IsvApply isvApply) {
        //遍历3次总共分3个状态(1. 已提交 2. 待审核 3. 通过/失败)
        for (int i = 0; i < 3; i++) {
            Map map = Maps.newHashMap();
            map.put("flag", (i == Integer.valueOf(isvApply.getStatus()) ? "1" : "0"));
            map.put("description", (i == Integer.valueOf(isvApply.getStatus()) ? isvApply.getDescription() != null ? isvApply.getDescription() : "" : ""));
            //如果遍历到了通过/失败
            if (i == 2) {
                if (STATUS_SUCCESS == Integer.valueOf(isvApply.getStatus()) || STATUS_FAILED == Integer.valueOf(isvApply.getStatus())) {
                    map.put("status", STATUS_SUCCESS != Integer.valueOf(isvApply.getStatus()) && STATUS_FAILED != Integer.valueOf(isvApply.getStatus()) ? "" : isvApply.getStatus());
                }
            }
            result.add(map);
        }
        return result;
    }

}
