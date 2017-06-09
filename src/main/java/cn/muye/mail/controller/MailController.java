package cn.muye.mail.controller;

import cn.muye.core.AjaxResult;
import cn.muye.mail.domain.Mail;
import cn.muye.mail.dto.MailDto;
import cn.muye.mail.service.MailService;
import cn.muye.utils.DateTimeUtils;
import cn.muye.utils.MailUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
