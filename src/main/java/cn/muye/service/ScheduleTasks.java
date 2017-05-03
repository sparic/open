package cn.muye.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/28.
 */
@Component
@Configurable
@EnableScheduling
public class ScheduleTasks {

    private Logger logger = Logger.getLogger(ScheduleTasks.class);

    public final static String UPLOAD_FILE = "http://localhost:8080/picture/uploadFile";

    @Autowired
    private PictureService pictureService;

    //每1分钟执行一次
    //@Scheduled(cron = "*/5 * *  * * * ")
    public void test(){
        System.out.println("Scheduling Tasks Examples By Cron: The time is now " + new Date());
    }

//    @Scheduled(cron = "0 0 3,20 * * * ")
//    public void uploadFileSchedule() {
//        logger.info("开始定时任务上传");
//        try {
//            Resource dest = new Resource(Constants.FILE_URL);
//            Resource[] files = dest.listFiles();
//            StringBuffer sb = new StringBuffer("");
//            String fileNames = null;
//            if(files.length == 0){
//                fileNames = sb.toString();
//            }else {
//                for (Resource file : files) {
//                    sb.append(file.getName()+",");
//                }
//                fileNames = sb.substring(0,sb.length()-1).toString();
//            }
//            Map<String,String> param = Maps.newHashMap();
//            param.put("fileNames", fileNames);
//            String result = HttpClientUtil.executePost(null,UPLOAD_FILE,param,null,null,null,true);
//            logger.info("文件上传结果是" + result);
//            logger.info("全部文件上传成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.info("上传文件出现了意外");
//        }
//    }


}
