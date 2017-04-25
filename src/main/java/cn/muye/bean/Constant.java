package cn.muye.bean;

import java.io.File;

/**
 * Created by Administrator on 2016/12/7.
 */
public class Constant {

    //public static final String FILE_URL = "f://download";
    public static final String FILE_URL = File.separator + "home" + File.separator + "robot" + File.separator + "share" + File.separator + "nfs";

    //用户最终满意程度
    public static final Integer UNSATISFY = 0; //对产品不满意
    public static final Integer SATISFY = 1; //对产品满意
    public static final Integer INTENTION = 2; //有意向
    public static final Integer TALK = 3; //愿意交流但无意向
    public static final Integer HATE = 4; //不愿交流，厌恶


}
