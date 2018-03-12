package cn.muye.mail.controller;

import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.employee.adminApi.service.AdminEmployeeService;
import cn.muye.employee.domain.Employee;
import cn.muye.mail.domain.Mail;
import cn.muye.mail.dto.MailDto;
import cn.muye.mail.manage.EmailSendManager;
import cn.muye.mail.model.SimpleEmail;
import cn.muye.mail.service.MailService;
import cn.muye.train.adminApi.service.AdminTrainService;
import cn.muye.train.domain.Train;
import cn.muye.utils.DateTimeUtils;
import cn.muye.utils.MailUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ray.Fu on 2017/5/10.
 */
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "admin/mail/{id}", method = RequestMethod.GET)
    @RequiresPermissions("mail:detail")
    @ApiOperation(value = "查看邮件详情", httpMethod = "GET", notes = "查看邮件详情")
    public AjaxResult getMailById(@ApiParam(value = "邮件ID") @PathVariable String id) {
        if (id == null || id.trim().length() == 0) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
        Mail mail = mailService.getById(Long.valueOf(id));
        return AjaxResult.success(objectToDto(mail), "查询成功");
    }

    private MailDto objectToDto(Mail mail) {
        MailDto dto = new MailDto();
        dto.setFromMail(mail.getFromMail());
        dto.setToMail(mail.getToMail());
        dto.setContext(mail.getContext());
        dto.setSendTime(DateTimeUtils.getDateString(mail.getSendTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setSubject(mail.getSubject());
        return dto;
    }

}
