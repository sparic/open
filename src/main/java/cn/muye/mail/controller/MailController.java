package cn.muye.mail.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.Constants;
import cn.muye.mail.domain.Mail;
import cn.muye.mail.dto.MailDto;
import cn.muye.mail.service.MailService;
import cn.muye.util.DateTimeUtils;
import cn.muye.util.MailUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Ray.Fu on 2017/5/10.
 */
@RestController
public class MailController {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "admin/mail/send", method = RequestMethod.POST)
    @ApiOperation(value = "发送邮件", httpMethod = "POST", notes = "发送邮件")
    public AjaxResult sendMail(@ApiParam(value = "邮件对象") @RequestBody String cooperationStr) {
        JSONObject jsonObject = JSON.parseObject(cooperationStr);
        String email = (String) jsonObject.get("email");
        String context = (String) jsonObject.get("context");
        String subject = (String) jsonObject.get("subject");
        String[] emailArr = new String[] {email};
        return AjaxResult.success(mailUtil.send(emailArr, subject, context));
    }

    @RequestMapping(value = "admin/mail/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查看邮件详情", httpMethod = "GET", notes = "查看邮件详情")
    public AjaxResult getMailById(@ApiParam(value = "邮件ID") @PathVariable Long id) {
        Mail mail = mailService.getById(id);
        return AjaxResult.success(objectToDto(mail));
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
