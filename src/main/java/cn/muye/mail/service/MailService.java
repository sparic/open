package cn.muye.mail.service;

import cn.muye.mail.domain.Mail;
import cn.muye.mail.mapper.MailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/10.
 */
@Service
@Transactional
public class MailService {

    private final static Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private MailMapper mailMapper;

    public void save(Mail mail) {
        mailMapper.save(mail);
    }

    public List<Mail> list() {
        return mailMapper.list();
    }

    public Mail getById(Long id) {
        return mailMapper.getById(id);
    }

    public void update(Mail mail) {
        mailMapper.update(mail);
    }
}
