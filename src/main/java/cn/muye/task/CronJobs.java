package cn.muye.task;

import java.util.Date;
import java.util.List;

import cn.muye.mail.domain.Mail;
import cn.muye.mail.service.MailService;
import cn.muye.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Component
public class CronJobs {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0/10 * * * * *")
    public void sendMail() {
        List<Mail> list = mailService.list();
        if (list != null && list.size() > 0) {
            for (Mail mail : list) {
                String[] toAddress = new String[]{mail.getToMail()};
                mailUtil.send(toAddress, mail.getSubject(), mail.getContext(), mail);
            }
        }

    }

}