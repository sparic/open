package cn.muye.mail.mapper;

import cn.muye.mail.domain.Mail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/10.
 */
public interface MailMapper {

    void save(Mail mail);

    List<Mail> list();

    Mail getById(@Param(value = "id") Long id);
}
