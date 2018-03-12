package cn.muye.mail.manage;

import cn.muye.mail.model.SimpleEmail;

import javax.mail.MessagingException;
import java.util.Map;

public interface EmailSendManager {

    /**
     * 发送简单邮件
     *
     * @param simpleEmail 简单邮件详情
     * @throws MessagingException
     */
    void sendEmail(SimpleEmail simpleEmail) throws MessagingException;

    /**
     * 使用Thymeleaf模板发送邮件
     *
     * @param simpleEmail      简单邮件详情
     * @param model            模板参数
     * @param templateLocation 模板所在路径
     * @throws MessagingException
     */
    void sendThymeleafEmail(SimpleEmail simpleEmail, Map<String, Object> model, String templateLocation)
            throws MessagingException;
}