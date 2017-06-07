package cn.muye.mail.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ray.Fu on 2017/5/10.
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = 4247883470622165759L;

    private Long id;

    private String fromMail;

    private String toMail;

    private String context;

    private String subject;

    private Date sendTime;

    private Boolean succeed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }
}
