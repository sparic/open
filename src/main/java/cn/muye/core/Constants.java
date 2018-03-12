package cn.muye.core;


/**
 * Created by Administrator on 2016/12/7.
 */
public class Constants {

    public final static String MAIL_HOST = "smtp.exmail.qq.com";
    public final static String MAIL_SENDER_ACCOUNT = "cloudmanager@mrobot.cn";
//    public final static String MAIL_SENDER_PSW = "Cloud161230"; //邮箱密码
    public final static String MAIL_SENDER_PSW = "QcH8G3bBkP3kBzUA"; //客户端专用密码【腾讯企业邮箱安全升级后需要用此密码登录】
    public final static int MAIL_HOST_PORT_465 = 465;
    public final static int MAIL_HOST_PORT_587 = 587;
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

    public final static int APP_AUTH_SN_LIMIT = 10; //最大APP授权数量

    public final static int APP_AUTH_VALID_PERIOD = 6; //授权有效时间(月)

    public final static String AES_KEY = "trOmVF7Uaqd4h9AN";

    public final static int VALID = 1; //有效
    public final static int NOT_VALID = 0; //无效

    public static final String EXPORT_DIR_NAME = "export"; //资源文件夹/导出
    public static final String EMPLOYEE_DIR_NAME = "employee"; //资源文件夹/导出/员工

    public static final Integer MAIL_STATUS_SEND = 1; //邮件状态：已发送
    public static final Integer MAIL_STATUS_NOT_SEND = 0; //邮件状态：未发送
}
