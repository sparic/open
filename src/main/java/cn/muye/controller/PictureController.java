package cn.muye.controller;

import cn.muye.bean.*;
import cn.muye.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/12/6.
 */
@Controller
public class PictureController {

    private Logger logger = Logger.getLogger(PictureController.class);
    private final Lock lock = new ReentrantLock();

    public final static String REMOTE_URL = "http://video.myee7.com/tarot_test"; //正式服务器地址
    //public final static String REMOTE_URL = "http://172.16.1.151:7070/tarot_test";
    //public final static String REMOTE_URL = "http://172.16.0.240:8082"; //测试服务器地址
    public final static String UPLOAD_URL = "/services/public/files/largeUpload"; //上传大文件的接口地址
    public final static String EXIST_URL = "/services/public/files/isExistFile"; //判断文件是否存在

   /* @Autowired
    private PictureService pictureService;

    *//**
     * 上传图片接口  暂不用
     * @param
     * @return
     *//*
    @RequestMapping(value = "picture/upload")
    @ResponseBody
    public AjaxResult pushPicture(@RequestParam("file") MultipartFile file){
        try {
            if(file.isEmpty()){
                return AjaxResult.failed("文件为空");
            }
            ApplicationHome home = new ApplicationHome(Application.class);
            Resource dest = home.getDir();
            dest = FileUtils.getFile(dest.getPath()+ Resource.separator + "picture" +Resource.separator+file.getOriginalFilename());
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            Picture picture = new Picture();
            picture.setPicUrl(Resource.separator + "picture" +Resource.separator+file.getOriginalFilename());
            picture.setCreateTime(new Date());
            pictureService.savePicture(picture);
            logger.info("上传成功");
            return AjaxResult.success();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("上传失败");
            return AjaxResult.failed("IO异常");
        }
    }*/

    /**
     * 获取图片
     * @param name
     * @param response
     */
    @RequestMapping(value = "picture/get/{name}")
      public void getPicture(@PathVariable("name")String name,HttpServletResponse response){
        try {
            //ApplicationHome home = new ApplicationHome();
            //Resource file = home.getDir();
            //file = FileUtils.getFile("F://download" + Resource.separator + name + ".jpeg");
            //Resource file = new Resource(Resource.separator + "media" + Resource.separator + "robot" + Resource.separator + "cooky" + Resource.separator + "nfs" + Resource.separator + name + ".jpeg");
            File file = new File(File.separator + "home" + File.separator + "robot" + File.separator + "share" + File.separator + "nfs" + File.separator + name + ".jpeg");
            if(file.isFile()){
                FileInputStream inputStream = new FileInputStream(file);
                byte[] data = new byte[(int)file.length()];
                int length = inputStream.read(data);
                inputStream.close();
                response.setCharacterEncoding("utf-8");
                response.setContentType("image/jpeg");
                OutputStream stream = response.getOutputStream();
                stream.write(data);
                stream.flush();
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存产品的满意度
     * @param picName
     * @param resultStatus
     * @return
     */
    @RequestMapping(value = "picture/save")
    @ResponseBody
    public AjaxResult savePicResult(@RequestParam("picName")String picName,
                                    @RequestParam("detailType")Integer detailType,
                                    @RequestParam("resultStatus")Integer resultStatus) {
        try {
            /*Picture picture = new Picture();
            picture.setPicId(picName);
            picture.setDetailType(detailType);
            picture.setResultStatus(resultStatus);
            picture.setCreateTime(new Date());*/
            if(logger.isInfoEnabled()){
                logger.info("图片:"+ picName + ",详细类型："+ detailType +",结果类型：" + resultStatus+ "---正准备添加" );
            }
            //pictureService.savePicture(picture);
           /* if(logger.isInfoEnabled()){
                logger.info("图片:"+ picName + "---添加成功" );
            }*/
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("图片：" + picName + "---添加失败");
            return AjaxResult.failed("保存失败");
        }

    }

    /**
     * 获取需要的文件的名称，单层目录
     * * @return
     */
    @RequestMapping(value = "picture/getFiles")
    @ResponseBody
    public AjaxResult getFiles(){
        try {
            //具体地址需修改
            File dest = new File(Constants.FILE_URL);
            List<FileDTO> files = Lists.newArrayList();
            listFiles(dest, files);
            return AjaxResult.success(files);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("系统出错");
        }
    }

    /**
     * 上传文件
     * @return
     */
    @RequestMapping(value = "picture/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("fileNames") String[] fileNames){
        if(lock.tryLock()){
            try {
                File dest = new File(Constants.FILE_URL);
                int size = fileNames.length;
                logger.info("本次上传文件个数为"+ size);
                for (String fileName : fileNames) {
                    String localUrl = dest.getAbsolutePath()+ File.separator + fileName;
                    File target = new File(localUrl);
                    if(target.exists() && target.isFile()){
                        //在上传文件之前 先判断是否已存在此文件
                        if(target.length()> 524288000 ){
                            logger.info("文件"+ fileName + "大于500M，无法上传");
                            continue;
                        }
                        Map<String,String> params = Maps.newHashMap();
                        params.put("fileName",fileName);
                        //params.put("md5", FileValidCreateUtil.fileMD5(localUrl));
                        String jsonResult = HttpClientUtil.executePost(null, REMOTE_URL + EXIST_URL, params, null, null, null, true);
                        FileResult fileResult = JSON.parseObject(jsonResult, FileResult.class);
                        if(fileResult.getStatus() == 0) {
                            if(!fileResult.isExist()){
                                long jumpSize = 0;
                                if(fileResult.isTemp()){
                                    jumpSize = fileResult.getSize();
                                }
                                //获取上传信息
                                String result = HttpClientUtil.executeUploadFile(null, REMOTE_URL+ UPLOAD_URL +"?fileName="+ fileName, localUrl,jumpSize, null, true);
                                AjaxResponse resp = JSON.parseObject(result, AjaxResponse.class);
                                if(resp.getStatus() == AjaxResponse.RESPONSE_STATUS_SUCCESS){
                                    //上传成功
                                    target.delete();
                                    continue;
                                }else if(resp.getStatus() == AjaxResponse.RESPONSE_STATUS_FAIURE) {
                                    //上传失败
                                    continue;
                                }
                            }else {
                                //假提示上传成功 同时删除本地文件
                                target.delete();
                                continue;
                            }
                        }else {
                            continue;
                        }
                    }else {
                        continue;
                    }
                }
                return AjaxResult.success("本次上传结束");
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                return AjaxResult.failed("连接时超时");
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                return AjaxResult.failed("http传输异常");
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.failed("传输流异常");
            } finally {
                this.lock.unlock();
            }
        }else {
            return AjaxResult.failed("暂时无法上传，请稍后");

        }

    }


    private void listFiles(File parentFile, List files) {
        if (!parentFile.exists() || !parentFile.isDirectory() || null == parentFile.listFiles()) {
            return;
        }
        for (File file : parentFile.listFiles()) {
            FileDTO resourceVo = new FileDTO(file, parentFile);
            files.add(resourceVo);
        }
    }


}
