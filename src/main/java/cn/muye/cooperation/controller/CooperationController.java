package cn.muye.cooperation.controller;

import cn.muye.core.AjaxResult;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.dto.IsvApplyDto;
import cn.muye.cooperation.service.AgentApplyService;
import cn.muye.cooperation.service.IsvApplyService;
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

    @Autowired
    private IsvApplyService isvApplyService;

    @RequestMapping(value = "/agentApply", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增代理商申请", httpMethod = "POST", notes = "新增代理商申请")
    public AjaxResult addAgentApply(@RequestBody String formObjectStr) {
        JSONObject jsonObject = JSONObject.parseObject(formObjectStr);
        String email = (String)((JSONObject) jsonObject.get("totalMan")).get("email");
        String contact = (String)((JSONObject)jsonObject.get("totalMan")).get("name");
        String password = String.valueOf(getRandomPassword());
        AgentApply agentApply = new AgentApply();
        agentApply.setCreateTime(new Date());
        agentApply.setContext(formObjectStr);
        agentApply.setContact(contact);
        agentApply.setUserName(email);
        agentApply.setPassword(password);
        agentApplyService.save(agentApply);
        return AjaxResult.success(objectToDto(agentApply), "您已提交申请，我们会在十个工作日内通知您");
    }

    @RequestMapping(value = "admin/agentApply/audit", method = RequestMethod.POST)
    @RequiresPermissions("agentApply:audit")
    @ResponseBody
    @ApiOperation(value = "审核代理商申请", httpMethod = "POST", notes = "审核代理商申请")
    public AjaxResult auditAgentApply(@RequestBody AgentApply agentApply) {
        try {
            Long id = agentApply.getId();
            if (id != null) {
                AgentApply agentApplyDb = agentApplyService.getById(id);
                if (agentApplyDb != null) {
                    agentApplyDb.setComment(agentApply.getComment());
                    agentApplyDb.setStatus(agentApply.getStatus());
                    agentApplyDb.setUpdateTime(new Date());
                    agentApplyService.update(agentApplyDb);
                    return AjaxResult.success();
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

    @RequestMapping(value = "/isvApply", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增ISV申请", httpMethod = "POST", notes = "新增ISV申请或审核")
    public AjaxResult addIsvApply(@RequestBody String formObjectStr) {
        JSONObject jsonObject = JSONObject.parseObject(formObjectStr);
        String email = (String)((JSONObject) jsonObject.get("totalMan")).get("email");
        String contact = (String)((JSONObject)jsonObject.get("totalMan")).get("name");
        String password = String.valueOf(getRandomPassword());
        IsvApply isvApply = new IsvApply();
        isvApply.setCreateTime(new Date());
        isvApply.setContext(formObjectStr);
        isvApply.setContact(contact);
        isvApply.setUserName(email);
        isvApply.setPassword(password);
        isvApplyService.save(isvApply);
        return AjaxResult.success(objectToDto(isvApply), "您已提交申请，我们会在十个工作日内通知您");
    }

    @RequestMapping(value = "admin/isvApply/audit", method = RequestMethod.POST)
    @RequiresPermissions("isvApply:audit")
    @ResponseBody
    @ApiOperation(value = "审核ISV申请", httpMethod = "POST", notes = "审核ISV申请")
    public AjaxResult auditIsvApply(@RequestBody IsvApply isvApply) {
        Long id = isvApply.getId();
        if (id != null) {
            IsvApply isvApplyDb = isvApplyService.getById(id);
            if (isvApplyDb != null) {
                isvApplyDb.setStatus(isvApply.getStatus());
                isvApplyDb.setUpdateTime(new Date());
                isvApplyDb.setComment(isvApply.getComment());
                isvApplyService.update(isvApplyDb);
                return AjaxResult.success();
            } else {
                return AjaxResult.failed("不存在该ISV申请");
            }
        } else {
            return AjaxResult.failed("不存在该ISV申请");
        }
    }


    @RequestMapping(value = {"admin/agentApply"}, method = RequestMethod.GET)
    @RequiresPermissions("agentApply:query")
    @ResponseBody
    @ApiOperation(value = "查询代理商申请列表", httpMethod = "GET", notes = "查询代理商申请列表")
    public AjaxResult listAgentApply(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                     @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<AgentApply> list = agentApplyService.list(page);
        List<AgentApplyDto> dtoList = Lists.newArrayList();
        if (list != null && list.size() > 0) {
            for (AgentApply agentApply : list) {
                dtoList.add(objectToDto(agentApply));
            }
        }
        PageInfo<AgentApplyDto> agentApplyDtoPageInfo = new PageInfo(list);
        agentApplyDtoPageInfo.setList(dtoList);
        return AjaxResult.success(agentApplyDtoPageInfo);
    }

    @RequestMapping(value = {"admin/isvApply"}, method = RequestMethod.GET)
    @RequiresPermissions("isvApply:query")
    @ResponseBody
    @ApiOperation(value = "查询ISV申请列表", httpMethod = "GET", notes = "查询Isv申请列表")
    public AjaxResult listIsvApply(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<IsvApply> list = isvApplyService.list(page);
        List<IsvApplyDto> dtoList = Lists.newArrayList();
        if (list != null && list.size() > 0) {
            for (IsvApply isvApply : list) {
                dtoList.add(objectToDto(isvApply));
            }
        }
        PageInfo<IsvApplyDto> isvApplyDtoPageInfo = new PageInfo(list);
        isvApplyDtoPageInfo.setList(dtoList);
        return AjaxResult.success(isvApplyDtoPageInfo);
    }

    @RequestMapping(value = "admin/agentApply/{id}", method = RequestMethod.GET)
    @RequiresPermissions("agentApply:detail")
    @ResponseBody
    @ApiOperation(value = "查询代理商申请详情", httpMethod = "GET", notes = "查询代理商申请详情")
    public AjaxResult getAgentApplyDetail(@PathVariable Long id) {
        AgentApply agentApply = agentApplyService.getById(id);
        return AjaxResult.success(objectToDto(agentApply));
    }

    @RequestMapping(value = "admin/isvApply/{id}", method = RequestMethod.GET)
    @RequiresPermissions("isvApply:detail")
    @ResponseBody
    @ApiOperation(value = "查询ISV申请详情", httpMethod = "GET", notes = "查询ISV申请详情")
    public AjaxResult getIsvApplyDetail(@PathVariable Long id) {
        IsvApply isvApply = isvApplyService.getById(id);
        return AjaxResult.success(objectToDto(isvApply));
    }

    private AgentApplyDto objectToDto(AgentApply agentApply) {
        AgentApplyDto dto = new AgentApplyDto();
        JSONObject jsonObject = JSONObject.parseObject(agentApply.getContext());
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("totalMan");
        String companyName = (String) jsonObject.get("company");
        String contact = (String) jsonObject1.get("name");
        String tel = (String) jsonObject1.get("tel");
        String phone = (String) jsonObject1.get("phone");
        dto.setCompanyName(companyName);
        dto.setContact(contact);
        dto.setCreateTime(DateTimeUtils.getDateString(agentApply.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setUpdateTime(DateTimeUtils.getDateString(agentApply.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setTel(tel);
        dto.setPhone(phone);
        dto.setStatus(agentApply.getStatus() == null ? "" : String.valueOf(agentApply.getStatus()));
        dto.setId(agentApply.getId());
        dto.setContext(agentApply.getContext());
        dto.setEmail(agentApply.getEmail());
        return dto;
    }

    private IsvApplyDto objectToDto(IsvApply isvApply) {
        IsvApplyDto dto = new IsvApplyDto();
        String companyName = (String)JSONObject.parseObject((String) (JSONObject.parseObject(isvApply.getContext())).get("totalMan")).get("name");
        dto.setCompanyName(companyName);
        dto.setContact(isvApply.getContact());
        dto.setContext(isvApply.getContext());
        dto.setCreateTime(DateTimeUtils.getDateString(isvApply.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setUpdateTime(DateTimeUtils.getDateString(isvApply.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setPhoneNumber(isvApply.getPhoneNumber());
        dto.setStatus(isvApply.getStatus());
        dto.setId(isvApply.getId());
        dto.setEmail(isvApply.getEmail());
        dto.setUserName(isvApply.getUserName());
        dto.setPassword(isvApply.getPassword());
        return dto;
    }

    private int getRandomPassword() {
        Random random = new Random();
        int randomNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        return randomNum;
    }
}
