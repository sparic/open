package cn.muye.cooperation.controller;

import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.service.AgentApplyService;
import cn.muye.user.domain.User;
import cn.muye.user.service.UserService;
import cn.muye.utils.DateTimeUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Ray.Fu on 2017/5/9.
 */
@Controller
public class CooperationController {

    @Autowired
    private AgentApplyService agentApplyService;

    private static final Integer STATUS_SUBMIT = 0; //已提交
    private static final Integer STATUS_AUDITING = 1; //待审核
    private static final Integer STATUS_SUCCESS = 2; //成功
    private static final Integer STATUS_FAILED = 3; //失败

    /*
    *新增代理上申请接口从业务角度转移到了用户注册
    @RequestMapping(value = "/agentApply", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增代理商申请", httpMethod = "POST", notes = "新增代理商申请")
    public AjaxResult addAgentApply(@RequestBody String formObjectStr) {
        JSONObject jsonObject = JSONObject.parseObject(formObjectStr);
        String email = (String)((JSONObject) jsonObject.get("totalMan")).get("email");
        String contact = (String)((JSONObject)jsonObject.get("totalMan")).get("name");
        String companyName = (String)jsonObject.get("company");
        String password = String.valueOf(getRandomPassword());
        User sameNameUser = userService.getUserByName(email);
        if (sameNameUser != null && sameNameUser.getUserName().equals(email)) {
            return AjaxResult.failed("负责人邮箱已存在，请更换邮箱");
        }
        AgentApply agentApply = new AgentApply();
        agentApply.setCreateTime(new Date());
        agentApplyService.save(agentApply);
        return AjaxResult.success(objectToDto(agentApply), Constants.FEEDBACK_WORD);
    }*/

    @RequestMapping(value = "admin/agentApply/audit", method = RequestMethod.POST)
    @RequiresPermissions("agentApply:audit")
    @ResponseBody
    @ApiOperation(value = "审核代理商申请", httpMethod = "POST", notes = "审核代理商申请")
    public AjaxResult auditAgentApply(@RequestBody AgentApply agentApply) {
        if (agentApply != null && agentApply.getStatus() == null) {
            return AjaxResult.failed("信息不全，请完善后再提交");
        }
        try {
            Long id = agentApply.getId();
            if (id != null) {
                AgentApply agentApplyDb = agentApplyService.getById(id);
                if (agentApplyDb != null) {
                    agentApplyDb.setStatus(agentApply.getStatus());
                    agentApplyDb.setUpdateTime(new Date());
                    agentApplyDb.setDescription(agentApply.getDescription());
                    agentApplyService.update(agentApplyDb);
                    String msg = null;
                    if (agentApplyDb.getStatus().equals(STATUS_AUDITING)) {
                        msg = "待审核，请耐心等待";
                    } else if (agentApplyDb.getStatus().equals(STATUS_SUCCESS)) {
                        msg = "认证通过，并发送通知邮件";
                    } else if (agentApplyDb.getStatus().equals(STATUS_FAILED)){
                        msg = "认证失败，并发送通知邮件";
                    }
                    return AjaxResult.success(objectToDto(agentApplyDb), msg);
                } else {
                    return AjaxResult.failed("不存在该代理商申请");
                }
            } else {
                return AjaxResult.failed("不存在该代理商申请");
            }
        } catch (UnauthenticatedException e) {
            return AjaxResult.failed("没有权限");
        } finally {
        }
    }

    @RequestMapping(value = {"admin/agentApply"}, method = RequestMethod.GET)
    @RequiresPermissions("agentApply:query")
    @ResponseBody
    @ApiOperation(value = "查询代理商申请列表", httpMethod = "GET", notes = "查询代理商申请列表")
    public AjaxResult listAgentApply(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                     @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     @ApiParam(value = "状态") @RequestParam(value = "status", required = false) Integer status) {
        PageHelper.startPage(page, pageSize);
        List<AgentApplyDto> list = agentApplyService.list(page, status);
        PageInfo<AgentApplyDto> agentApplyDtoPageInfo = new PageInfo(list);
        agentApplyDtoPageInfo.setList(list);
        return AjaxResult.success(agentApplyDtoPageInfo);
    }

    @RequestMapping(value = "admin/agentApply/{id}", method = RequestMethod.GET)
    @RequiresPermissions("agentApply:detail")
    @ResponseBody
    @ApiOperation(value = "查询代理商申请详情", httpMethod = "GET", notes = "查询代理商申请详情")
    public AjaxResult getAgentApplyDetail(@PathVariable Long id) {
        AgentApplyDto agentApplyDto = agentApplyService.getByIdWithUser(id);
        return AjaxResult.success(agentApplyDto);
    }

    private AgentApplyDto objectToDto(AgentApply agentApply) {
        AgentApplyDto dto = new AgentApplyDto();
        dto.setCreateTime(DateTimeUtils.getDateString(agentApply.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setUpdateTime(DateTimeUtils.getDateString(agentApply.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setStatus(agentApply.getStatus() == null ? "" : String.valueOf(agentApply.getStatus()));
        dto.setId(agentApply.getId()== null ? "" : String.valueOf(agentApply.getId()));
        return dto;
    }
}
