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

    public final static Long SUPER_ADMIN_ID = 1L; //超级管理员ID
    public final static Long CUSTOMER_ROLE_ID = 2L; //普通用户

    public static final Integer GOLD_AGENT = 1; //黄金代理商
    public static final Integer PLATINUM_AGENT = 2; //铂金代理商
    public static final Integer DIAMOND_AGENT = 3; //钻石代理商
    public static final Integer CUSTOMER_AGENT = 0; //普通代理商

    public final static String TYPE_AUDIT_AGENT_APPLY = "audit"; //走代理商审核接口
    public final static String TYPE_UPDATE_AGENT_APPLY = "udpate"; //走代理商申请更新接口

    public final static String SHIRO_SALT = "sdk1234"; //走代理商申请更新接口

    public final static int APP_AUTH_SN_LIMIT = 10;

    public final static String AES_KEY = "trOmVF7Uaqd4h9AN";
}
