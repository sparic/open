package cn.muye.cooperation.adminApi.controller;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.dto.IsvApplyDto;
import cn.muye.cooperation.adminApi.service.AdminAgentApplyService;
import cn.muye.cooperation.adminApi.service.AdminIsvApplyService;
import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.utils.DateTimeUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/9.
 */
@Controller
public class AdminCooperationController {

    @Autowired
    private AdminAgentApplyService adminAgentApplyService;

    @Autowired
    private AdminIsvApplyService adminIsvApplyService;

    /**
     * 代理商认证审核
     * @param agentApply
     * @return
     */
    @RequestMapping(value = "admin/agentApply/audit", method = RequestMethod.POST)
    @RequiresPermissions("agentApply:audit")
    @ResponseBody
    @ApiOperation(value = "后台审核代理商申请", httpMethod = "POST", notes = "后台审核代理商申请")
    public AjaxResult auditAgentApply(@RequestBody AgentApply agentApply) {
        if (agentApply != null && agentApply.getStatus() == null) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "信息不全，请完善后再提交");
        }
        try {
            AgentApply agentApplyDb = adminAgentApplyService.getById(agentApply.getId());
            if (agentApplyDb != null) {
                agentApplyDb.setStatus(agentApply.getStatus());
                agentApplyDb.setUpdateTime(new Date());
                agentApplyDb.setDescription(agentApply.getDescription());
                String msg = adminAgentApplyService.update(agentApplyDb, Constants.TYPE_AUDIT_AGENT_APPLY);
                return AjaxResult.success(agentObjectToDtoAdmin(agentApplyDb), msg);
            } else {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该代理商申请");
            }
        } catch (UnauthenticatedException e) {
            return AjaxResult.failed(AjaxResult.CODE_NOT_AUTHORIZED_FAILED, "没有权限");
        } finally {
        }

    }

    /**
     * ISV认证审核
     * @param isvApply
     * @return
     */
    @RequestMapping(value = "admin/isvApply/audit", method = RequestMethod.POST)
    @RequiresPermissions("isvApply:audit")
    @ResponseBody
    @ApiOperation(value = "后台审核ISV申请", httpMethod = "POST", notes = "后台审核ISV申请")
    public AjaxResult auditIsvApply(@RequestBody IsvApply isvApply) {
        if (isvApply != null && isvApply.getStatus() == null || isvApply.getId() == null || (isvApply.getStatus().equals(ApplyStatusType.SUCCESS.getValue()) && isvApply.getLevel() == null)) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "信息不全，请完善后再提交");
        }
        try {
            IsvApply isvApplyDb = adminIsvApplyService.getById(isvApply.getId());
            if (isvApplyDb != null) {
                isvApplyDb.setStatus(isvApply.getStatus());
                isvApplyDb.setUpdateTime(new Date());
                isvApplyDb.setDescription(isvApply.getDescription());
                isvApplyDb.setLevel(isvApply.getLevel());
                String msg = adminIsvApplyService.update(isvApplyDb, Constants.TYPE_AUDIT_AGENT_APPLY);
                return AjaxResult.success(isvObjectToDtoAdmin(isvApplyDb), msg);
            } else {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该ISV申请");
            }
        } catch (UnauthenticatedException e) {
            return AjaxResult.failed(AjaxResult.CODE_NOT_AUTHORIZED_FAILED, "没有权限");
        } finally {
        }

    }

    /**
     * 代理商认证列表查询
     * @param page
     * @param pageSize
     * @param statusListStr
     * @return
     */
    @RequestMapping(value = {"admin/agentApply"}, method = RequestMethod.GET)
    @RequiresPermissions("agentApply:query")
    @ResponseBody
    @ApiOperation(value = "后台查询代理商申请列表", httpMethod = "GET", notes = "后台查询代理商申请列表")
    public AjaxResult listAgentApply(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                     @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     @ApiParam(value = "状态") @RequestParam(value = "status", required = false) String statusListStr) {
        List<Integer> statusList = JSON.parseArray(statusListStr, Integer.class);
        PageHelper.startPage(page, pageSize, true, null, true);
        List<AgentApplyDto> list = adminAgentApplyService.list(page, statusList);
        PageInfo<AgentApplyDto> agentApplyDtoPageInfo = new PageInfo(list);
        agentApplyDtoPageInfo.setList(list);
        return AjaxResult.success(agentApplyDtoPageInfo, "查询成功");
    }

    /**
     * ISV认证列表查询
     * @param page
     * @param pageSize
     * @param statusListStr
     * @return
     */
    @RequestMapping(value = {"admin/isvApply"}, method = RequestMethod.GET)
    @RequiresPermissions("isvApply:query")
    @ResponseBody
    @ApiOperation(value = "后台查询ISV申请列表", httpMethod = "GET", notes = "后台查询ISV申请列表")
    public AjaxResult listIsvApply(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                     @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     @ApiParam(value = "状态") @RequestParam(value = "status", required = false) String statusListStr) {
        List<Integer> statusList = JSON.parseArray(statusListStr, Integer.class);
        PageHelper.startPage(page, pageSize, true, null, true);
        List<IsvApplyDto> list = adminIsvApplyService.list(page, statusList);
        PageInfo<IsvApplyDto> isvApplyDtoPageInfo = new PageInfo(list);
        isvApplyDtoPageInfo.setList(list);
        return AjaxResult.success(isvApplyDtoPageInfo, "查询成功");
    }

    /**
     * 后台查询代理商申请详情
     * @param id
     * @return
     */
    @RequestMapping(value = "admin/agentApply/{id}", method = RequestMethod.GET)
    @RequiresPermissions("agentApply:detail")
    @ResponseBody
    @ApiOperation(value = "后台查询代理商申请详情", httpMethod = "GET", notes = "后台查询代理商申请详情")
    public AjaxResult getAgentApplyDetail(@PathVariable Long id) {
        AgentApplyDto agentApplyDto = adminAgentApplyService.getByIdWithUser(id);
        return AjaxResult.success(agentApplyDto, "查询成功");
    }

    /**
     * 后台查询ISV申请详情
     * @param id
     * @return
     */
    @RequestMapping(value = "admin/isvApply/{id}", method = RequestMethod.GET)
    @RequiresPermissions("isvApply:detail")
    @ResponseBody
    @ApiOperation(value = "后台查询ISV申请详情", httpMethod = "GET", notes = "后台查询ISV申请详情")
    public AjaxResult getIsvApplyDetail(@PathVariable Long id) {
        IsvApplyDto isvApplyDto = adminIsvApplyService.getByIdWithUser(id);
        return AjaxResult.success(isvApplyDto, "查询成功");
    }

    private AgentApplyDto agentObjectToDtoAdmin(AgentApply agentApply) {
        AgentApplyDto dto = new AgentApplyDto();
        dto.setCreateTime(DateTimeUtils.getDateString(agentApply.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setUpdateTime(DateTimeUtils.getDateString(agentApply.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setStatus(agentApply.getStatus() == null ? "" : String.valueOf(agentApply.getStatus()));
        dto.setId(agentApply.getId() == null ? "" : String.valueOf(agentApply.getId()));
        return dto;
    }

    private IsvApplyDto isvObjectToDtoAdmin(IsvApply isvApply) {
        IsvApplyDto dto = new IsvApplyDto();
        dto.setCreateTime(DateTimeUtils.getDateString(isvApply.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setUpdateTime(DateTimeUtils.getDateString(isvApply.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setStatus(isvApply.getStatus() == null ? "" : String.valueOf(isvApply.getStatus()));
        dto.setId(isvApply.getId() == null ? "" : String.valueOf(isvApply.getId()));
        return dto;
    }
}
