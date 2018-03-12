package cn.muye.config;

import cn.muye.core.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by Ray.fu on 2018/3/7.
 */
@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setDefaultEncoding("UTF-8");
        mailSenderImpl.setHost(Constants.MAIL_HOST);
        mailSenderImpl.setPort(Integer.valueOf(Constants.MAIL_HOST_PORT_587));
        mailSenderImpl.setUsername(Constants.MAIL_SENDER_ACCOUNT);
        mailSenderImpl.setPassword(Constants.MAIL_SENDER_PSW);
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout",25000+"");
        p.setProperty("mail.smtp.auth","true");
        mailSenderImpl.setJavaMailProperties(p);
        return mailSenderImpl;
    }
}
