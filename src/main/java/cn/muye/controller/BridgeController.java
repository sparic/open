package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.DeadAuthInfo;
import cn.muye.bean.RequestJson;
import cn.muye.bean.RespDataInfo;
import cn.muye.cache.CachePublicInfo;
import cn.muye.cache.CacheSystemInfoManager;
import cn.muye.service.OrderConfigService;
import cn.muye.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class BridgeController {
    private Logger logger = Logger.getLogger(BridgeController.class);

    @Autowired
    private OrderConfigService orderConfigService;

    public static final String clientPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbNojYr8KlqKD/y"+
            "COd7QXu3e4TsrHd4sz3XgDYWEZZgYqIjVDcpcnlztwomgjMj9xSxdpyCc85GOGa0"+
            "lva1fNZpG6KXYS1xuFa9G7FRbaACoCL31TRv8t4TNkfQhQ7e2S7ZktqyUePWYLlz"+
            "u8hx5jXdriErRIx1jWK1q1NeEd3NAgMBAAECgYAws7Ob+4JeBLfRy9pbs/ovpCf1"+
            "bKEClQRIlyZBJHpoHKZPzt7k6D4bRfT4irvTMLoQmawXEGO9o3UOT8YQLHdRLitW"+
            "1CYKLy8k8ycyNpB/1L2vP+kHDzmM6Pr0IvkFgnbIFQmXeS5NBV+xOdlAYzuPFkCy"+
            "fUSOKdmt3F/Pbf9EhQJBANrF5Uaxmk7qGXfRV7tCT+f27eAWtYi2h/gJenLrmtke"+
            "Hg7SkgDiYHErJDns85va4cnhaAzAI1eSIHVaXh3JGXcCQQDDL9ns78LNDr/QuHN9"+
            "pmeDdlQfikeDKzW8dMcUIqGVX4WQJMptviZuf3cMvgm9+hDTVLvSePdTlA9YSCF4"+
            "VNPbAkEAvbe54XlpCKBIX7iiLRkPdGiV1qu614j7FqUZlAkvKrPMeywuQygNXHZ+"+
            "HuGWTIUfItQfSFdjDrEBBuPMFGZtdwJAV5N3xyyIjfMJM4AfKYhpN333HrOvhHX1"+
            "xVnsHOew8lGKnvMy9Gx11+xPISN/QYMa24dQQo5OAm0TOXwbsF73MwJAHzqaKZPs"+
            "EN08JunWDOKs3ZS+92maJIm1YGdYf5ipB8/Bm3wElnJsCiAeRqYKmPpAMlCZ5x+Z"+
            "AsuC1sjcp2r7xw==";

    public static final String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmzaI2K/Cpaig/8gjne0F7t3uE"+
            "7Kx3eLM914A2FhGWYGKiI1Q3KXJ5c7cKJoIzI/cUsXacgnPORjhmtJb2tXzWaRui"+
            "l2EtcbhWvRuxUW2gAqAi99U0b/LeEzZH0IUO3tku2ZLaslHj1mC5c7vIceY13a4h"+
            "K0SMdY1itatTXhHdzQIDAQAB";

	public static final String  serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALIZ98KqgLW8IMt4"+
												"G+N+4d3DiOiEa+5s6lCMSGE/NbU9stJEqw0EuCP54MY6JkT0HCYTCrLXqww6rSQy"+
												"WF7BNCVGssk2XDcvSKiCz1ZMgabd6XVK5kvIycySydXQ0Ky6rnfxw8w2mllHABFv"+
												"s1eamaHQozv18n/XGqemjW2BFy/jAgMBAAECgYAxT3FCi3SBXKnzy7hk/z9H6Bhi"+
												"0C8V3z/stzpe+mJDYOa+wtZdD15wT4HFQFpSIwgcHo+Kvp2UEDbZ27qN2Y43AZbF"+
												"9LOalWTRUzYtr8wL8MIbgtew/QQ9YFNWdkTZ6MxCItjD/mSz3Lrkcphvbsx4VoCV"+
												"YIJ04r+Loi0t9g0guQJBANvkpfrq0bLVRYWfaigjkx47mr0trJkB7mjADe69Iqts"+
												"M/2x5dHPpClDK78yzAWxU2BrYzOd31QIOm32iMIvRxUCQQDPWJPMOzcq8Jqs1PAM"+
												"7D0hxnvF3tSJB0CJCQWdGFkJiuIYSbrWnCVF78jJyU2AK1H3RDi9BzGPL2Z3i2Si"+
												"+9kXAkAPnKtAJl3fEY9PDmNuGCCA3AB/f/eqIV345/HVSm5kt1j1oSTNAa4JE/DO"+
												"MWAU42MlDFrNtl69y5vCZOeOyeaFAkBOJieGmWcAozDZJWTYqg2cdk/eU08t2nLj"+
												"c2gPPscIRrVSzC9EhhOyWV8HVv0D6s/471inPlfajNYFBp/Goj+/AkEAiejHX/58"+
												"Vv8+ccW22RMZmyxiHcZpTw9hz7vHUCWv03+fyVGtGMhJ4xuPt8UaZm91yHSPWWar"+
												"M8Xa7errKaXN9A==";
	public static final String  serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyGffCqoC1vCDLeBvjfuHdw4jo"+
												"hGvubOpQjEhhPzW1PbLSRKsNBLgj+eDGOiZE9BwmEwqy16sMOq0kMlhewTQlRrLJ"+
												"Nlw3L0iogs9WTIGm3el1SuZLyMnMksnV0NCsuq538cPMNppZRwARb7NXmpmh0KM7"+
												"9fJ/1xqnpo1tgRcv4wIDAQAB";

    @RequestMapping(value = "getServerKey", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult getServerKey(@RequestParam("data")String data,
                                   @RequestParam("encryptKey")String encryptKey, HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        String publicKey = null;
        JSONObject jsonObj = this.getJSONObject(data, encryptKey);
        publicKey = jsonObj.getString("publicKey");
        String token = jsonObj.getString("token");
        String clientRandomNumber = jsonObj.getString("clientRandomNumber");
        String clientTimeStamp = jsonObj.getString("clientTimeStamp");
        String robotNum = jsonObj.getString("robotNum");
        logger.info("startAccessInterface=getServerKey" +
                ",accessTime="+startTime+"" +
                ",submit parameter"+
                ",publicKey="+publicKey+"" +
                ",token="+token+
                ",clientRandomNumber="+clientRandomNumber+
                ",clientTimeStamp="+clientTimeStamp+
                ",robotNum="+robotNum+
                ",ip="+IPUtils.getIpAddr(request));
        if(StringUtils.isEmpty(clientRandomNumber) || StringUtils.isEmpty(clientTimeStamp)){
            logger.error("验签失败N or T！");
            return AjaxResult.failed("验签失败data n t错误");
        }
        if(StringUtils.isEmpty(publicKey)){
            logger.error("验签失败p！");
            return AjaxResult.failed("验签失败data错误!");
        }
        String secretKey = CacheSystemInfoManager.getAccessKeyCache("QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX");
        if(StringUtils.isEmpty(secretKey)){
            CacheSystemInfoManager.insertAccessKeyInfoCache("QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX", "cpB95knP5djLOvCSkqkqqmulMxTfYaUg1GAXlxhm");
        }
        //验证token有效性
        if(!checkToken(token)){
            logger.error("token存在问题");
            return AjaxResult.failed("token存在问题");
        }
        try {
            //生成publicInfo
            String[] tokenArray = token.split(":");
            CachePublicInfo publicInfoCache = CacheSystemInfoManager.getPublicInfoCache(tokenArray[0]);
            if(null != publicInfoCache && publicInfoCache.getToken().equals(token)){
                logger.error("token错误");
                return AjaxResult.failed("token错误");
            }
            Map<String, String> map = RSA.generateKeyPair();
            CachePublicInfo publicInfo = new CachePublicInfo();
            publicInfo.setAccessKey(tokenArray[0]);
            publicInfo.setClientPublicKey(publicKey);
            publicInfo.setToken(token);
            publicInfo.setRandomNumber(RandomUtil.getRandomNum(6));
            publicInfo.setTimeStamp(System.currentTimeMillis() + "");
            publicInfo.setClientRandomNumber(clientRandomNumber);
            publicInfo.setClientTimeStamp(clientTimeStamp);
            publicInfo.setServerPrivateKey(map.get("privateKey"));
            publicInfo.setServerPublicKey(map.get("publicKey"));
            //如果存在从缓存中移除
            if(null != publicInfoCache){
                CacheSystemInfoManager.removePublicInfoCache(tokenArray[0]);
            }
            System.out.println("缓存参数 加密：clientPublicKey=" + publicInfo.getClientPublicKey() + "\n,serverPrivateKey=" + publicInfo.getServerPrivateKey() + "\n,serverPublicKey=" + publicInfo.getServerPublicKey());
            //增加至缓存
            CacheSystemInfoManager.insertPublicInfoCache(tokenArray[0], publicInfo);
            //设置加密的参数，随机数和时间戳
            TreeMap<String, Object> params = new TreeMap<String, Object>();
            params.put("randomNumber", publicInfo.getRandomNumber());
            params.put("timeStamp", publicInfo.getTimeStamp());
            params.put("clientRandomNumber", clientRandomNumber);
            params.put("clientTimeStamp", clientTimeStamp);
            params.put("publicKey", publicInfo.getServerPublicKey());

            //加密参数
            RespDataInfo respDataInfo = getEncryption(null, params);
            logger.info("endAccessInterface=getServerKey" +
                    ",totalTime ="+(System.currentTimeMillis() - startTime)+
                    ",return parameter,randomNumber=" + publicInfo.getRandomNumber() +
                    ",timeStamp=" + publicInfo.getTimeStamp() +
                    ",clientRandomNumber=" + clientRandomNumber +
                    ",clientTimeStamp=" + clientTimeStamp +
                    ",publicKey=" + publicInfo.getServerPublicKey());
            //返回加密的参数和serverPublicKey
            return AjaxResult.success(respDataInfo);
        } catch (Exception e) {
            logger.error("数据错误");
            return AjaxResult.failed("数据错误");
        }
    }


    @RequestMapping(value = "bridge", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult bridge(@RequestParam("token")String token,
                             @RequestParam("data")String data, HttpServletRequest request) {
        try{
            long startTime = System.currentTimeMillis();
            logger.info("startAccessInterface=bridge"+
                    ",startTime="+startTime+
                    ",submit parameter"+
                    ",token="+token+
                    ",data="+data+
                    ",ip="+IPUtils.getIpAddr(request));
            String secretKey = CacheSystemInfoManager.getAccessKeyCache("QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX");
            if(StringUtils.isEmpty(secretKey)){
                CacheSystemInfoManager.insertAccessKeyInfoCache("QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX", "cpB95knP5djLOvCSkqkqqmulMxTfYaUg1GAXlxhm");
            }
            //验证token有效性
            if(!checkToken(token)){
                logger.error("token存在问题");
                return AjaxResult.failed("token存在问题");
            }
            //判断是否已经提交
            String[] tokenArray = token.split(":");
            CachePublicInfo publicInfoCache = CacheSystemInfoManager.getPublicInfoCache(tokenArray[0]);
            if(null != publicInfoCache && publicInfoCache.getToken().equals(token)){
                logger.error("token错误");
                return AjaxResult.failed("token错误");
            }
            JSONObject jsonObj = JSONObject.parseObject(data);
            String clientRandomNumber = jsonObj.getString("clientRandomNumber");
            String clientTimeStamp = jsonObj.getString("clientTimeStamp");
            String robotNum = jsonObj.getString("robotNum");
            String userId = jsonObj.getString("userId");
            String question = jsonObj.getString("question");
            String platform = jsonObj.getString("platform");
            CachePublicInfo publicInfo = new CachePublicInfo();
            publicInfo.setAccessKey(tokenArray[0]);
            publicInfo.setToken(token);
            publicInfo.setClientRandomNumber(clientRandomNumber);
            publicInfo.setClientTimeStamp(clientTimeStamp);
            //如果存在从缓存中移除
            if(null != publicInfoCache){
                CacheSystemInfoManager.removePublicInfoCache(tokenArray[0]);
            }
            //增加至缓存
            CacheSystemInfoManager.insertPublicInfoCache(tokenArray[0], publicInfo);
            //
            boolean hasKeyWords = orderConfigService.hasKeyWords(question);
            if (hasKeyWords){
                platform = "wap";
            }else {
                platform = "web";
            }
            //开始访问移动接口
            String jsonResult = partnerJsonResult(userId, question, platform);
            //结束访问移动接口
            TreeMap<String, Object> params = new TreeMap<String, Object>();
            params.put("clientRandomNumber", publicInfo.getClientRandomNumber());
            params.put("clientTimeStamp", publicInfo.getClientTimeStamp());
            params.put("robotNum", robotNum);
            params.put("userId", userId);
            params.put("question", question);
            params.put("jsonResult", jsonResult);
            logger.info("endAccessInterface=bridge" +
                    ",startTime=" + startTime +
                    ",totalTime=" + (System.currentTimeMillis() - startTime) +
                    ",return parameter" +
                    ",robotNum=" + robotNum +
                    ",userId=" + userId +
                    ",question=" + question +
                    ",platform=" + platform +
                    ",clientRandomNumber=" + publicInfo.getClientRandomNumber() +
                    ",clientTimeStamp=" + publicInfo.getClientRandomNumber() +
                    ",jsonResult=" + jsonResult);
            //返回加密的参数
            return AjaxResult.success(params);
        } catch (Exception e) {
            logger.error("数据错误发生异常!");
            return AjaxResult.failed("数据错误发生异常");
        }
    }

    private String partnerJsonResult(String userId, String question, String platform){
        long startTime1 = System.currentTimeMillis();
        String jsonResult = null;
        logger.info("access huNanMobile Interface start,startTime:" + startTime1);
        try {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("method", "XIAOI_HQ_HNAN_DoAsk");
//                    params.put("format", "json");
//                    params.put("appId", "506034");
//                    params.put("status", "1");
//                    params.put("timestamp", DateUtil.getTimeStamp());
//                    params.put("flowId", DateUtil.getTimeRandom());
//                    params.put("userId", userId);
//                    params.put("question", question);
////                    params.put("location", location);
//                    params.put("platform", "web");
////                    params.put("brand", brand);
//                    String url = "https://111.8.20.248:9091/oppf";
//                    return HttpsClientUtil.sendSSLPostRequest(url, params);

//            String json = JSON.toJSONString(new RequestJson(question, "web", userId));
//            String url = "https://111.8.20.248:9091/oppf?method=XIAOI_HQ_HNAN_DoAsk&format=json&appId=506034&status=1&timestamp="+DateUtil.getTimeStamp()+"&flowId="+DateUtil.getTimeRandom();

            jsonResult = HttpsClientUtil.sendSSLPostRequestJson(
                    "https://111.8.20.248:9091/oppf?method=XIAOI_HQ_HNAN_DoAsk&format=json&appId=506034&status=1&timestamp=" + DateUtil.getTimeStamp() + "&flowId=" + DateUtil.getTimeRandom(),
                    JSON.toJSONString(new RequestJson(question, platform, userId)));
            //替换字符串
            jsonResult = jsonResult.replace("小I","移娃").replace("智能客服机器人","移娃");
        }catch (Exception e){
            logger.error("access huNanMobile Interface fail!");
        }
        logger.info("access huNanMobile Interface end,TotalTime:" + (System.currentTimeMillis() - startTime1));
        return jsonResult;
    }


//    @RequestMapping(value = "bridge", method= RequestMethod.POST)
//    @ResponseBody
//    public AjaxResult bridge(@RequestParam("key")String key,
//                             @RequestParam("data")String data,
//                             @RequestParam("encryptKey")String encryptKey, HttpServletRequest request) {
//        try{
//            long startTime = System.currentTimeMillis();
//            if(StringUtils.isEmpty(key)){
//                logger.error("key有误");
//                return AjaxResult.failed("key有误");
//            }
//            CachePublicInfo publicInfo = CacheSystemInfoManager.getPublicInfoCache(key);
//            if(null == publicInfo){
//                logger.error("token错误！");
//                return AjaxResult.failed("token错误！");
//            }
//            // 验签
//            boolean passSign = false;
//            try {
////                passSign = EncryUtil.checkDecryptAndSign(data,
////                        encryptKey, publicInfo.getClientPublicKey(), publicInfo.getServerPrivateKey());
//                passSign = EncryUtil.checkDecryptAndSign(data,
//                        encryptKey, clientPublicKey, serverPrivateKey);
//            } catch (Exception e) {
//                logger.error("数据有误！", e);
//                return AjaxResult.failed("数据有误！");
//            }
//
//            if(passSign){
//                // 验签通过
//                String aeskey = null;
//                try {
////                    aeskey = RSA.decrypt(encryptKey,
////                            publicInfo.getServerPrivateKey());
//                    aeskey = RSA.decrypt(encryptKey, serverPrivateKey);
//                } catch (Exception e) {
//                    logger.error("验证失败 e！",e);
//                    return AjaxResult.failed("验证失败 e！");
//                }
//                if(null == aeskey){
//                    logger.error("验证失败！");
//                    return AjaxResult.failed("验证失败！");
//                }
//                String JSONdata = AES.decryptFromBase64(data, aeskey);
//
//                JSONObject jsonObj = JSONObject.parseObject(JSONdata);
//                String randomNumber = jsonObj.getString("randomNumber");
//                String timeStamp = jsonObj.getString("timeStamp");
//                String clientRandomNumber = jsonObj.getString("clientRandomNumber");
//                String clientTimeStamp = jsonObj.getString("clientTimeStamp");
//                String robotNum = jsonObj.getString("robotNum");
//                if(StringUtils.isEmpty(randomNumber) || !randomNumber.equals(publicInfo.getRandomNumber())){
//                    logger.error("参数错误！");
//                    return AjaxResult.failed("参数错误！");
//                }
//                if(StringUtils.isEmpty(timeStamp) || !timeStamp.equals(publicInfo.getTimeStamp())){
//                    logger.error("参数错误");
//                    return AjaxResult.failed("参数错误");
//                }
//                if(StringUtils.isEmpty(clientRandomNumber) || !clientRandomNumber.equals(publicInfo.getClientRandomNumber())){
//                    logger.error("参数错误n！");
//                    return AjaxResult.failed("参数错误n！");
//                }
//                if(StringUtils.isEmpty(clientTimeStamp) || !clientTimeStamp.equals(publicInfo.getClientTimeStamp())){
//                    logger.error("参数错误t");
//                    return AjaxResult.failed("参数错误t");
//                }
//                String userId = jsonObj.getString("userId");
//                String question = jsonObj.getString("question");
////                String location = jsonObj.getString("location");
////                String platform = jsonObj.getString("platform");
////                String brand = jsonObj.getString("brand");
//                logger.info("startAccessInterface=bridge"+
//                        ",accessTime="+startTime+
//                        ",submit parameter"+
//                        ",key="+key+
//                        ",randomNumber="+randomNumber+
//                        ",timeStamp="+timeStamp+
//                        ",clientRandomNumber="+clientRandomNumber+
//                        ",clientTimeStamp="+clientTimeStamp+
//                        ",robotNum="+robotNum+
//                        ",userId="+userId+
//                        ",question="+question+
//                        ",ip="+IPUtils.getIpAddr(request));
//                String url = null;
//                String jsonResult = null;
//                long startTime1 = System.currentTimeMillis();
//                logger.info("access huNanMobile Interface start,startTime:"+startTime1);
//                try {
////                    Map<String, String> params = new HashMap<String, String>();
////                    params.put("method", "XIAOI_HQ_HNAN_DoAsk");
////                    params.put("format", "json");
////                    params.put("appId", "506034");
////                    params.put("status", "1");
////                    params.put("timestamp", DateUtil.getTimeStamp());
////                    params.put("flowId", DateUtil.getTimeRandom());
////                    params.put("userId", userId);
////                    params.put("question", question);
//////                    params.put("location", location);
////                    params.put("platform", "web");
//////                    params.put("brand", brand);
////                    url = "https://111.8.20.248:9091/oppf";
////                    jsonResult = HttpsClientUtil.sendSSLPostRequest(url, params);
//                }catch (Exception e){
//                    logger.error("access huNanMobile Interface fail!");
//                }
//                logger.info("access huNanMobile Interface end,TotalTime:"+(System.currentTimeMillis() - startTime1));
//                //设置加密的参数，随机数和时间戳
//                TreeMap<String, Object> params = new TreeMap<String, Object>();
//                params.put("randomNumber", publicInfo.getRandomNumber());
//                params.put("timeStamp", publicInfo.getTimeStamp());
//                params.put("clientRandomNumber", publicInfo.getClientRandomNumber());
//                params.put("clientTimeStamp", publicInfo.getClientTimeStamp());
////            params.put("type", "1");
////            params.put("content", "流量办理");
//                params.put("jsonResult", jsonResult);
//                //加密参数
//                RespDataInfo respDataInfo = getEncryption(publicInfo, params);
//                logger.info("endAccessInterface=bridge" +
//                        ",totalTime=" + (System.currentTimeMillis() - startTime)+
//                        ",return parameter" +
//                        ",randomNumber=" + publicInfo.getRandomNumber() +
//                        ",timeStamp=" + publicInfo.getTimeStamp() +
//                        ",clientRandomNumber=" + publicInfo.getClientRandomNumber() +
//                        ",clientTimeStamp=" + publicInfo.getClientRandomNumber() +
//                        ",jsonResult=" + jsonResult);
//                //返回加密的参数
//                return AjaxResult.success(respDataInfo);
//
//            }else{
//                logger.error("验签失败！");
//                return AjaxResult.failed("验签失败！");
//            }
//
//        } catch (Exception e) {
//            logger.error("数据错误发生异常!");
//            return AjaxResult.failed("数据错误发生异常");
//        }
//    }


    @RequestMapping(value = "test", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult test(@RequestParam("param")String param) {
        try{
            logger.info("test,开始访问时间："+System.currentTimeMillis());
//        String url = "http://111.8.20.250:19001/oppf?method=DQ_HQ_HNAN_GroupReferQuery&format=json&appId=502047&status=1&timestamp=20160324150325&PROVINCE_CODE=HNAN&TRADE_DEPART_PASSWD=HNWC09&TRADE_STAFF_ID=ITFWC000&TRADE_EPARCHY_CODE=0731&TRADE_CITY_CODE=0000&TRADE_DEPART_ID=00302&ROUTE_EPARCHY_CODE=0000&IN_MODE_CODE=3&TRADE_TERMINAL_ID=10.154.92.35&flowId=10002261";
//        String url = "http://10.170.134.39/hunan_mobile_proxy/oppf?method=DQ_HQ_HNAN_GroupReferQuery&format=json&appId=502047&status=1&timestamp=20160324150325&PROVINCE_CODE=HNAN&TRADE_DEPART_PASSWD=HNWC09&TRADE_STAFF_ID=ITFWC000&TRADE_EPARCHY_CODE=0731&TRADE_CITY_CODE=0000&TRADE_DEPART_ID=00302&ROUTE_EPARCHY_CODE=0000&IN_MODE_CODE=3&TRADE_TERMINAL_ID=10.154.92.35&flowId=10002261";
            String url = "http://111.8.20.248:9001/oppf?method=XIAOI_HQ_HNAN_DoAsk&format=json&appId=506034&status=1&timestamp="+DateUtil.getTimeStamp()+"&flowId="+DateUtil.getTimeRandom()+"&question=你好&platform=web";
            String jsonResult = HttpClientUtil.executePost(null, url, null, null, null, null, true);
            logger.info("test,结束访问成功："+System.currentTimeMillis());
            return AjaxResult.success(jsonResult, "访问成功");
        } catch (Exception e) {
            logger.error("数据错误");
            return AjaxResult.failed("数据错误");
        }
    }


    //检查token是否有效
    private boolean checkToken(String sendToken){
        if(StringUtils.isEmpty(sendToken)){
            return false;
        }
        String[] token = sendToken.split(":");
        if(token == null || (token != null && token.length < 3)){
            return false;
        }
        if(StringUtils.isEmpty(token[0])){
            return false;
        }
        String secretKey = CacheSystemInfoManager.getAccessKeyCache(token[0]);
        if(secretKey == null){
            return false;
        }
        if(StringUtils.isEmpty(token[2])){
            return false;
        }
        String deadStr = new String(Base64.decode2(token[2]));
        if(null == deadStr){
            return false;
        }
        DeadAuthInfo deadAuthInfo = null;
        try {
            deadAuthInfo = JSON.parseObject(deadStr, DeadAuthInfo.class);
        }catch (Exception e){
        }
        if(deadAuthInfo == null){
            return false;
        }
        if(deadAuthInfo.getDeadline() < DateUtil.getSecondsOfCurrentMillis()){
            return false;
        }
        Auth auth = Auth.create(token[0], secretKey);
        String authToken = auth.sendTokenWithDeadline(deadAuthInfo.getDeadline());
        if(!StringUtils.isEmpty(authToken) && authToken.equals(sendToken)){
            return true;
        }else{
            return false;
        }
    }

    //加密参数
    private RespDataInfo getEncryption(CachePublicInfo publicInfo, TreeMap<String, Object> params){
        RespDataInfo respDataInfo = new RespDataInfo();
        try {
            String clientKey = null;
            String privateKey = null;
//            if(null == publicInfo){
                clientKey = clientPublicKey;
                privateKey = serverPrivateKey;
//            }else{
//                clientKey = publicInfo.getClientPublicKey();
//                privateKey = publicInfo.getServerPrivateKey();
//                System.out.println("加密：clientPublicKey="+clientKey+"\n,serverPrivateKey="+privateKey);
//            }
            // 生成RSA签名
            String sign = EncryUtil.handleRSA(params, privateKey);
            params.put("sign", sign);

            String info = JSON.toJSONString(params);
            //随机生成AES密钥
            String aesKey = RandomUtil.getRandom(16);
            //AES加密数据
            String data = AES.encryptToBase64(info, aesKey);

            // 使用RSA算法将商户自己随机生成的AESkey加密
            String encryptKey  = RSA.encrypt(aesKey, clientKey);
            respDataInfo.setData(data);
            respDataInfo.setEncryptKey(encryptKey);
            if(null != publicInfo){
                System.out.println("加密：encryptKey="+encryptKey+"\n,data="+data);
            }
        } catch (Exception e) {
            return null;
        }
        return respDataInfo;
    }

    private JSONObject getJSONObject(String data, String encryptKey){
        // 验签
        boolean passSign = false;
        try {
            passSign = EncryUtil.checkDecryptAndSign(data,
                    encryptKey, clientPublicKey, serverPrivateKey);
        } catch (Exception e) {
            logger.error("数据有误passSign-->checkDecryptAndSign！", e);
            return null;
        }
        if(passSign){
            // 验签通过
            String aeskey = null;
            try {
                aeskey = RSA.decrypt(encryptKey,
                        serverPrivateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(null == aeskey){
                logger.error("验证失败:null == aeskey！");
                return null;
            }
            String JSONdata = AES.decryptFromBase64(data, aeskey);

            JSONObject jsonObj = JSONObject.parseObject(JSONdata);
            return jsonObj;
        }else{
            logger.error("验签失败！");
            return null;
        }
    }
    
}
