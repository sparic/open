package cn.muye.utils;

import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.mail.domain.Mail;
import cn.muye.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Ray.Fu on 2017/5/10.
 */
@Component
public class MailUtil {

    private final static Logger LOG = LoggerFactory.getLogger(MailUtil.class);

    private JavaMailSenderImpl email;

    private SimpleMailMessage message;

    @Autowired
    private MailService mailService;

    public MailUtil() {
        // TODO Auto-generated constructor stub
        email = new JavaMailSenderImpl();
        email.setHost(Constants.MAIL_HOST);
        email.setUsername(Constants.MAIL_SENDER_ACCOUNT);
        email.setPassword(Constants.MAIL_SENDER_PSW);//授权码
        email.setPort(Constants.MAIL_HOST_PORT);
        Properties properties = new Properties();
        properties.put(Constants.MAIL_AUTH, true);
        properties.put(Constants.MAIL_SSL, true);
        properties.put(Constants.MAIL_SOCKET_CLASS, Constants.MAIL_CREATE_FACTORY);
        properties.put(Constants.MAIL_TIMEOUT_CLASS, Constants.MAIL_TIMEOUT_MILE_SECONDS);
        email.setJavaMailProperties(properties);
    }

    //发送邮件
    public AjaxResult send(final String[] to, final String subject, String context, Mail mail) {
        try {
            message = new SimpleMailMessage();
            message.setFrom(Constants.MAIL_SENDER_ACCOUNT);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(context);
            email.send(message);
            //修改任务记录
            mail.setSucceed(true);
            mailService.update(mail);
            return AjaxResult.success("发送成功!");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "发送失败!");
        }

        /* //按模板发送
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");   // 设置 utf-8防止出现乱码
                message.setTo(to);
                message.setFrom(new InternetAddress(Constants.MAIL_SENDER_ACCOUNT));
                message.setSubject(subject);
                Map model = Maps.newHashMap();
                model.put("url", "http://www.jiaobuchong.com/uuid/userId/1242425353");
                model.put("img", "http://172.16.0.151:18080/devResource/portrait.jpg");
                model.put("currentDate", "2016-03-31");

                message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mailModule.vm", "utf-8", model), true);
            }
        };
        try {
            this.email.send(preparator);
        } catch (MailException ex) {
            ex.printStackTrace();
        }
        return AjaxResult.success();*/
    }
}
