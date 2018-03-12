package cn.muye.mail.manage.impl;

import cn.muye.core.Constants;
import cn.muye.mail.manage.EmailSendManager;
import cn.muye.mail.model.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * Created by sparic on 2018/3/7.
 */
@Service
public class EmailSendManagerImpl implements EmailSendManager {

    @Autowired
    private SpringTemplateEngine thymeleafEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(SimpleEmail simpleEmail) throws MessagingException {

    }

    @Override
    public void sendThymeleafEmail(SimpleEmail simpleEmail, Map<String, Object> model, String templateLocation)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, simpleEmail.isAttachment());

        /**
         * 添加发送者
         */
        helper.setFrom(Constants.MAIL_SENDER_ACCOUNT);

        Set<String> toSet = simpleEmail.getToSet();
        /**
         * 添加接收者
         */
        helper.setTo(toSet.toArray(new String[toSet.size()]));

        /**
         * 添加主题
         */
        helper.setSubject(simpleEmail.getSubject());
        /**
         * 添加正文
         */
        if(model != null){
            Context context = new Context();
            for(Map.Entry<String, Object> param : model.entrySet()){
                context.setVariable(param.getKey(), param.getValue());
            }

            String emailContent = thymeleafEngine.process("emailTemplate", context);
            helper.setText(emailContent,true);
        }

        /**
         * 添加附件
         */
        if(simpleEmail.isAttachment()){
            Map<String, File> attachments = simpleEmail.getAttachments();

            if(attachments != null){
                for(Map.Entry<String, File> attach : attachments.entrySet()){
                    helper.addAttachment(attach.getKey(), attach.getValue());
                }
            }
        }
        mailSender.send(message);  //发送
    }
}
