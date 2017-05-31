package cn.muye.core;

import java.io.File;

/**
 * Created by Administrator on 2016/12/7.
 */
public class Constants {

    public final static String MAIL_HOST = "smtp.exmail.qq.com";
    public final static String MAIL_SENDER_ACCOUNT = "cloudmanager@mrobot.cn";
    public final static String MAIL_SENDER_PSW = "Cloud161230";
    public final static int MAIL_HOST_PORT = 465;
    public final static String MAIL_AUTH = "mail.smtp.auth";
    public final static String MAIL_SSL = "mail.smtp.ssl.enable";
    public final static String MAIL_SOCKET_CLASS = "mail.smtp.socketFactory.class";
    public final static String MAIL_CREATE_FACTORY = "javax.net.ssl.SSLSocketFactory";
    public final static String MAIL_TIMEOUT_CLASS = "mail.smtp.timeout";
    public final static long MAIL_TIMEOUT_MILE_SECONDS = 25000;

    public final static Long SUPER_ADMIN_ID = 5L; //超级管理员ID
    public final static Long AGENT_ROLE_ID = 8L; //普通代理商ID
    public final static Long CUSTOMER_ROLE_ID = 9L; //普通代理商ID
}
