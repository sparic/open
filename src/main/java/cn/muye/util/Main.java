package cn.muye.util;

import cn.muye.bean.RequestJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * AES+RSA签名，加密 验签，解密
* @ClassName: Main 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author enva
* @date 2017年1月23日 上午1:14:27
*
 */
public class Main 
{
//	public static final String clientPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbNojYr8KlqKD/y"+
//												"COd7QXu3e4TsrHd4sz3XgDYWEZZgYqIjVDcpcnlztwomgjMj9xSxdpyCc85GOGa0"+
//												"lva1fNZpG6KXYS1xuFa9G7FRbaACoCL31TRv8t4TNkfQhQ7e2S7ZktqyUePWYLlz"+
//												"u8hx5jXdriErRIx1jWK1q1NeEd3NAgMBAAECgYAws7Ob+4JeBLfRy9pbs/ovpCf1"+
//												"bKEClQRIlyZBJHpoHKZPzt7k6D4bRfT4irvTMLoQmawXEGO9o3UOT8YQLHdRLitW"+
//												"1CYKLy8k8ycyNpB/1L2vP+kHDzmM6Pr0IvkFgnbIFQmXeS5NBV+xOdlAYzuPFkCy"+
//												"fUSOKdmt3F/Pbf9EhQJBANrF5Uaxmk7qGXfRV7tCT+f27eAWtYi2h/gJenLrmtke"+
//												"Hg7SkgDiYHErJDns85va4cnhaAzAI1eSIHVaXh3JGXcCQQDDL9ns78LNDr/QuHN9"+
//												"pmeDdlQfikeDKzW8dMcUIqGVX4WQJMptviZuf3cMvgm9+hDTVLvSePdTlA9YSCF4"+
//												"VNPbAkEAvbe54XlpCKBIX7iiLRkPdGiV1qu614j7FqUZlAkvKrPMeywuQygNXHZ+"+
//												"HuGWTIUfItQfSFdjDrEBBuPMFGZtdwJAV5N3xyyIjfMJM4AfKYhpN333HrOvhHX1"+
//												"xVnsHOew8lGKnvMy9Gx11+xPISN/QYMa24dQQo5OAm0TOXwbsF73MwJAHzqaKZPs"+
//												"EN08JunWDOKs3ZS+92maJIm1YGdYf5ipB8/Bm3wElnJsCiAeRqYKmPpAMlCZ5x+Z"+
//												"AsuC1sjcp2r7xw==";
//
//	public static final String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmzaI2K/Cpaig/8gjne0F7t3uE"+
//													"7Kx3eLM914A2FhGWYGKiI1Q3KXJ5c7cKJoIzI/cUsXacgnPORjhmtJb2tXzWaRui"+
//													"l2EtcbhWvRuxUW2gAqAi99U0b/LeEzZH0IUO3tku2ZLaslHj1mC5c7vIceY13a4h"+
//													"K0SMdY1itatTXhHdzQIDAQAB";
	
//	public static final String  serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALIZ98KqgLW8IMt4"+
//												"G+N+4d3DiOiEa+5s6lCMSGE/NbU9stJEqw0EuCP54MY6JkT0HCYTCrLXqww6rSQy"+
//												"WF7BNCVGssk2XDcvSKiCz1ZMgabd6XVK5kvIycySydXQ0Ky6rnfxw8w2mllHABFv"+
//												"s1eamaHQozv18n/XGqemjW2BFy/jAgMBAAECgYAxT3FCi3SBXKnzy7hk/z9H6Bhi"+
//												"0C8V3z/stzpe+mJDYOa+wtZdD15wT4HFQFpSIwgcHo+Kvp2UEDbZ27qN2Y43AZbF"+
//												"9LOalWTRUzYtr8wL8MIbgtew/QQ9YFNWdkTZ6MxCItjD/mSz3Lrkcphvbsx4VoCV"+
//												"YIJ04r+Loi0t9g0guQJBANvkpfrq0bLVRYWfaigjkx47mr0trJkB7mjADe69Iqts"+
//												"M/2x5dHPpClDK78yzAWxU2BrYzOd31QIOm32iMIvRxUCQQDPWJPMOzcq8Jqs1PAM"+
//												"7D0hxnvF3tSJB0CJCQWdGFkJiuIYSbrWnCVF78jJyU2AK1H3RDi9BzGPL2Z3i2Si"+
//												"+9kXAkAPnKtAJl3fEY9PDmNuGCCA3AB/f/eqIV345/HVSm5kt1j1oSTNAa4JE/DO"+
//												"MWAU42MlDFrNtl69y5vCZOeOyeaFAkBOJieGmWcAozDZJWTYqg2cdk/eU08t2nLj"+
//												"c2gPPscIRrVSzC9EhhOyWV8HVv0D6s/471inPlfajNYFBp/Goj+/AkEAiejHX/58"+
//												"Vv8+ccW22RMZmyxiHcZpTw9hz7vHUCWv03+fyVGtGMhJ4xuPt8UaZm91yHSPWWar"+
//												"M8Xa7errKaXN9A==";
//	public static final String  serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyGffCqoC1vCDLeBvjfuHdw4jo"+
//												"hGvubOpQjEhhPzW1PbLSRKsNBLgj+eDGOiZE9BwmEwqy16sMOq0kMlhewTQlRrLJ"+
//												"Nlw3L0iogs9WTIGm3el1SuZLyMnMksnV0NCsuq538cPMNppZRwARb7NXmpmh0KM7"+
//												"9fJ/1xqnpo1tgRcv4wIDAQAB";




//	public static final String serverPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIYboUFCt8HCxdqyyTvHfU3obIFdrHejbU7aZZNe3VRPNkKMJ0HfybJM7pXpBLr+Jn3e60zjwxqPfhLGjdxG2oUBzdxPvYBmyaLY9DdOFq2IhTip4s2DbEIlcjndleZWTUrSbemfKVvX9Bl744nIv8ZJfQM83MYATmJBDCuG3FanAgMBAAECgYAdL7A1msaRNcmHojdnejAMH5RVx0Q8uWWVrrRCkXfL3H1rmEMG9CRaWKHoIFF2PM+1vykoj70rb6U5gtrm4Mi3rpW1RFSBg7CXIJKEdxgkL8YAl3cKLiC5EgZ581a4ju9dsPzPFrrYZgpnv2DKFQ1uLrqfNgV9m4+WHssDS8upuQJBAM9q9zM3omeZhPZHvw+4sKghGCTiX2w7CUgZ+pTP0AGFInvR3fOfaOFOvn2jB0x/oX831Rd8QIn6+gIaC/pKvfUCQQClhOj81HLbxgtOSBU15wms5LQsfHFfRnzSgw4OBCMd4O7qIzbzyGz2M2LSl20cii1d3VTCfGbuSlS6oHSutSSrAkEAqyuW/Ul4WQ60T5o97nd4Mge9qRK3tNWzfzERPiWRfu+j2ZMEG7z+DJGzWnNj3pmlaRtmW49Ko5PfJkTghZmQ0QJADc4KCJxHgCECbGMCsd41JmBkzpkKc6HTY1Ne5rhdnW5XPtv1B1weCmK6yUmr5gd4RS3ild3+T72PV4lucevVxQJBAIjVrq8GZliBJfwCAkh+f90Ned8LQpvOTchivO7Pxyc++BGKLttBgiHrLwXKdwMOjETmMZ2/zfm/kaXfNtfpN+M=";
//	public static final String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGG6FBQrfBwsXassk7x31N6GyBXax3o21O2mWTXt1UTzZCjCdB38myTO6V6QS6/iZ93utM48Maj34Sxo3cRtqFAc3cT72AZsmi2PQ3ThatiIU4qeLNg2xCJXI53ZXmVk1K0m3pnylb1/QZe+OJyL/GSX0DPNzGAE5iQQwrhtxWpwIDAQAB";


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


//	public static void main(String[] args) throws Exception {
//		TreeMap<String, Object> params = new TreeMap<String, Object>();
//		params.put("userid", "152255855");
//		params.put("phone", "18965621420");
//		params.put("randomNumber", "893037");
//		params.put("timeStamp", "1486549519597");
//
//
//		String ACCESSKEY = "QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX";//accessKey，测试机
//		String SECRETKEY = "cpB95knP5djLOvCSkqkqqmulMxTfYaUg1GAXlxhm";//secretKey，测试机
//		Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
//		System.out.println("dddddddddddddd="+auth.sendToken(1800));
//
//
//		client(params);
//
//		server();
//
//
//		RSA.generateKeyPair();
//		clientGetServerPublic();
//		hunanMobile();
//		hunanMobile_test();
//	}


	public static void hunanMobile_test() throws Exception {
		String ACCESSKEY = "QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX";//accessKey，测试机
		String SECRETKEY = "cpB95knP5djLOvCSkqkqqmulMxTfYaUg1GAXlxhm";//secretKey，测试机
		Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
//		System.out.println("dddddddddddddd="+auth.sendToken(1800));
		String token = auth.sendToken(1800);

		try{
			Map<String, String> params = new HashMap<String, String>();

			TreeMap<String, Object> params1 = new TreeMap<String, Object>();
			params1.put("clientRandomNumber", RandomUtil.getRandomNum(6));
			params1.put("clientTimeStamp", System.currentTimeMillis() + "");
			params1.put("robotNum", "111111111111test");
			params1.put("userId", "15111010193");
			params1.put("question", "你好");
			params.put("data", JSON.toJSONString(params1));
			params.put("token", token);
//			String url = "https://localhost:9443/bridge";//测试
			String url = "https://www.myee7.com/hunan_mobile/bridge";//测试

			String jsonResult = HttpsClientUtil.sendSSLPostRequestParams(url, params);
			System.out.println("jsonResult==="+jsonResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void hunanMobile() throws Exception {
////		Map<String, String> params1 = new HashMap<String, String>();
////		params1.put("method", "XIAOI_HQ_HNAN_DoAsk");
////		params1.put("format", "json");
////		params1.put("appId", "506034");
////		params1.put("status", "1");
////		params1.put("timestamp", DateUtil.getTimeStamp());
////		params1.put("flowId", DateUtil.getTimeRandom());
////		params1.put("userId", "18274978715");
////		params1.put("question", "你好");
//////                    params.put("location", location);
////		params1.put("platform", "web");
//////                    params.put("brand", brand);
////		String url = "https://111.8.20.248:9091/oppf";
////		String content = HttpsClientUtil.sendSSLPostRequest(url, params1);
//////		String content = HttpsClientUtil.sendSSLPostRequest("https://www.myee7.com/hunan_mobile/getServerKey", params1);
//////		String content = HttpsClientUtil.sendSSLPostRequest("https://111.8.20.248:9443/oppf", params1);
//////		String content = HttpsDemo.sslRequestPost("https://120.24.174.163/hunan_test/getServerKey", params1);
////		JSONObject result = JSONObject.parseObject(content);
////		System.out.println("加密后的请求数据:\n" + result.getString("data"));
////		System.out.println("加密后的请求数据1:\n" + JSONObject.parseObject(result.getString("data")).get("data"));
////		System.out.println("加密后的请求数据2:\n" + JSONObject.parseObject(result.getString("data")).get("encryptkey"));
//
////		String url = "http://111.8.20.248:9001/oppf?method=XIAOI_HQ_HNAN_DoAsk&format=json&appId=506034&status=1&timestamp="+DateUtil.getTimeStamp()+"&flowId="+DateUtil.getTimeRandom()+"&question=你好&platform=web";
////		String jsonResult = HttpClientUtil.executePost(null, url, null, null, null, null, true);
////		System.out.println("加密后的请求数据2:\n" + jsonResult);
//
//		Map<String, String> params1 = new HashMap<String, String>();
//		params1.put("param1", "1");
////		String content = HttpsClientUtil.sendSSLPostRequest("https://www.myee7.com/hunan_mobile/test", params1);
//		String content = HttpsClientUtil.sendSSLPostRequest("https://www.myee7.com/hunan_mobile/test1", params1);
////		String content = HttpsClientUtil.sendSSLPostRequest("https://120.24.174.163/hunan_test/getServerKey", params1);
////		String content = HttpsDemo.sslRequestPost("https://120.24.174.163/hunan_test/getServerKey", params1);


		try{
//			logger.info("test,开始访问时间："+System.currentTimeMillis());
			Map<String, String> params = new HashMap<String, String>();
//			params.put("method", "XIAOI_HQ_HNAN_DoAsk");
//			params.put("format", "json");
//			params.put("appId", "506034");
//			params.put("status", "1");
//			params.put("timestamp", DateUtil.getTimeStamp());
//			params.put("flowId", DateUtil.getTimeRandom());
//			params.put("userId", "18818117192");
//			params.put("question", "你好");
////                    params.put("location", location);
//			params.put("platform", "web");
////                    params.put("brand", brand);
////			String url = "https://111.8.20.248:9091/oppf";//正式
////			String url = "https://111.8.20.250:19001/oppf";//测试
					//appId=510272//测试
			        //appId=506034//正式

			RequestJson requestJson = new RequestJson(
//					"XIAOI_HQ_HNAN_DoAsk",
//					"json",
//					"1",
//					"506034",
//					DateUtil.getTimeStamp(),
//					DateUtil.getTimeRandom(),
					"你好",
					"web",
					"18818117192");
			String json = JSON.toJSONString(requestJson);
//			params.put("content", json);
			String url = "https://111.8.20.248:9091/oppf?method=XIAOI_HQ_HNAN_DoAsk&format=json&appId=506034&status=1&timestamp="+DateUtil.getTimeStamp()+"&flowId="+DateUtil.getTimeRandom();
			String jsonResult = HttpsClientUtil.sendSSLPostRequestJson(url, json);
			System.out.println("jsonResult==="+jsonResult);
//			logger.info("test,结束访问成功："+System.currentTimeMillis());
//			return AjaxResult.success(jsonResult, "访问成功");
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("数据错误");
//			return AjaxResult.failed("数据错误");
		}

//		System.out.println("加密后的请求数据:\n" + content);
	}


	public static void clientGetServerPublic() throws Exception {

		Map<String, String> map = RSA.generateKeyPair();
		String ACCESSKEY = "QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX";//accessKey，测试机
		String SECRETKEY = "cpB95knP5djLOvCSkqkqqmulMxTfYaUg1GAXlxhm";//secretKey，测试机
		Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
//		System.out.println("dddddddddddddd="+auth.sendToken(1800));
		String token = auth.sendToken(1800);
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("token", token);
		params.put("publicKey", map.get("publicKey"));
		params.put("clientRandomNumber", RandomUtil.getRandomNum(6));
		params.put("clientTimeStamp", System.currentTimeMillis() + "");

		// 生成RSA签名
		String sign = EncryUtil.handleRSA(params, clientPrivateKey);
		params.put("sign", sign);

		String info = JSON.toJSONString(params);
		//随机生成AES密钥
		String aesKey = RandomUtil.getRandom(16);
		//AES加密数据
		String data = AES.encryptToBase64(info, aesKey);

		// 使用RSA算法将商户自己随机生成的AESkey加密
		String encryptkey = RSA.encrypt(aesKey, serverPublicKey);


		Map<String, String> params1 = new HashMap<String, String>();
		params1.put("data", data);
		params1.put("encryptKey", encryptkey);
//		String content = HttpsClientUtil.sendSSLPostRequest("https://www.myee7.com/hunan_mobile/getServerKey", params1);
		String content = HttpsClientUtil.sendSSLPostRequestParams("https://localhost:9443/getServerKey", params1);
//		String content = HttpsDemo.sslRequestPost("https://120.24.174.163/hunan_test/getServerKey", params1);
		JSONObject result = JSONObject.parseObject(content);
		System.out.println("加密后的请求数据:\n" + result.getString("data"));
		System.out.println("加密后的请求数据1:\n" + JSONObject.parseObject(result.getString("data")).get("data"));
		System.out.println("加密后的请求数据2:\n" + JSONObject.parseObject(result.getString("data")).get("encryptKey"));

		String encryptKey1 = JSONObject.parseObject(result.getString("data")).get("encryptKey").toString();
		String data2 = JSONObject.parseObject(result.getString("data")).get("data").toString();
		clientBridge(data2, encryptKey1, token, map);
//		Req.data = data;
//		Req.encryptkey = encryptkey;
//
//		System.out.println("加密后的请求数据:\n" + new Req().toString());
	}


	public static void clientBridge(String data, String encryptKey, String token, Map<String, String> map) throws Exception {
		//客户端解密

		// 验签
		boolean passSign = false;
		try {
			passSign = EncryUtil.checkDecryptAndSign(data,
					encryptKey, serverPublicKey, clientPrivateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(passSign) {
			// 验签通过
			String aeskey = null;
			try {
				aeskey = RSA.decrypt(encryptKey,
						clientPrivateKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null == aeskey) {
				System.out.println("加密后的请求数据aeskey == null");
			}
			String JSONdata = AES.decryptFromBase64(data, aeskey);

			JSONObject jsonObj = JSONObject.parseObject(JSONdata);
			String randomNumber = jsonObj.getString("randomNumber");
			String timeStamp = jsonObj.getString("timeStamp");
			String clientRandomNumber = jsonObj.getString("clientRandomNumber");
			String clientTimeStamp = jsonObj.getString("clientTimeStamp");
			String publicKey = jsonObj.getString("publicKey");

			//请求服务端


			TreeMap<String, Object> params2 = new TreeMap<String, Object>();
			params2.put("randomNumber", randomNumber);
			params2.put("timeStamp", timeStamp);
			params2.put("clientRandomNumber", clientRandomNumber);
			params2.put("clientTimeStamp", clientTimeStamp);
			params2.put("userId", "18818117192");
			params2.put("question", "你好");

			// 生成RSA签名
			String sign = EncryUtil.handleRSA(params2, map.get("privateKey"));
//			String sign = EncryUtil.handleRSA(params2, clientPrivateKey);
			params2.put("sign", sign);

			String info1 = JSON.toJSONString(params2);
			//随机生成AES密钥
			String aesKey1 = RandomUtil.getRandom(16);
			//AES加密数据
			String data1 = AES.encryptToBase64(info1, aesKey1);

			// 使用RSA算法将商户自己随机生成的AESkey加密
			String encryptkey1 = RSA.encrypt(aesKey1, publicKey);
//			String encryptkey1 = RSA.encrypt(aesKey1, serverPublicKey);
			String[] tokenArray = token.split(":");

			Map<String, String> params3 = new HashMap<String, String>();
			params3.put("data", data1);
			params3.put("encryptKey", encryptkey1);
			params3.put("key", tokenArray[0]);
//			params3.put("token", token);
			String content1 = HttpsClientUtil.sendSSLPostRequestParams("https://www.myee7.com/hunan_mobile/bridge", params3);
//			String content1 = HttpsClientUtil.sendSSLPostRequest("https://localhost:9443/bridge", params3);
			JSONObject result1 = JSONObject.parseObject(content1);
			String data2 = JSONObject.parseObject(result1.getString("data")).get("data").toString();
			String encryptKey2 = JSONObject.parseObject(result1.getString("data")).get("encryptKey").toString();
//			dec(data2, encryptKey2, publicKey, map);
			System.out.println("加密后的请求数据5:\n" + result1.getString("data"));
			System.out.println("加密后的请求数据6:\n" + JSONObject.parseObject(result1.getString("data")).getString("data"));
			System.out.println("加密后的请求数据7:\n" + JSONObject.parseObject(result1.getString("data")).getString("encryptKey"));
			System.out.println("加密后的请求数据8:\n" + JSONObject.parseObject(result1.getString("data")).get("jsonResult"));

		}
	}


	private static void dec(String data, String encryptKey, String publicServerKey, Map<String, String> map){
		// 验签
		boolean passSign = false;
		String clientPrivateKey1 = map.get("privateKey");
		System.out.println("客户端解密：encryptKey="+encryptKey+"\n,data="+data+"\n,publicServerKey="+publicServerKey+"\n,clientPrivateKey="+clientPrivateKey+"\n,clientPublicKey="+map.get("publicKey"));
		try {
//			passSign = EncryUtil.checkDecryptAndSign(data,
//					encryptKey, publicServerKey, clientPrivateKey);
			passSign = EncryUtil.checkDecryptAndSign(data,
					encryptKey, serverPublicKey, clientPrivateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(passSign) {
			// 验签通过
			String aeskey = null;
			try {
//				aeskey = RSA.decrypt(encryptKey, clientPrivateKey);
				aeskey = RSA.decrypt(encryptKey, clientPrivateKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null == aeskey) {
				System.out.println("加密后的请求数据aeskey == null");
			}
			String JSONdata = AES.decryptFromBase64(data, aeskey);

			JSONObject jsonObj = JSONObject.parseObject(JSONdata);
			String randomNumber = jsonObj.getString("randomNumber");
			String timeStamp = jsonObj.getString("timeStamp");
			String clientRandomNumber = jsonObj.getString("clientRandomNumber");
			String clientTimeStamp = jsonObj.getString("clientTimeStamp");
			String jsonResult = jsonObj.getString("jsonResult");

			System.out.println("加密后的请求数据randomNumber:\n" + randomNumber);
			System.out.println("加密后的请求数据timeStamp:\n" + timeStamp);
			System.out.println("加密后的请求数据clientRandomNumber:\n" + clientRandomNumber);
			System.out.println("加密后的请求数据clientTimeStamp:\n" + clientTimeStamp);
			System.out.println("加密后的请求数据jsonResult:\n" + jsonResult);
		}
	}

	
	public static void client(TreeMap<String, Object> params) throws Exception {
		String clientPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbNojYr8KlqKD/y"+
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
		String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDM3RkuNWkZzxSbsqYP8mcKklebLrxtoebp3JDAoN6tYsRO5X4TmYr32wrphkpQwMQscbt4LjVx4Vj4JPBj/EsTcF8ExvWuEVKBrlQ25Ad7KjBVJtKX9wuKeqUC55dvCr5Aocqyde+IjxCoJrLvU+cRhZ+NXDAqVcr9MQSCHBN2qwIDAQAB";
		// 生成RSA签名
		String sign = EncryUtil.handleRSA(params, clientPrivateKey);
		params.put("sign", sign);
		
		String info = JSON.toJSONString(params);
		//随机生成AES密钥
		String aesKey = RandomUtil.getRandom(16);
		//AES加密数据
		String data = AES.encryptToBase64(info, aesKey);
		
		// 使用RSA算法将商户自己随机生成的AESkey加密
		String encryptkey = RSA.encrypt(aesKey, serverPublicKey);
		
		Req.data = data;
		Req.encryptkey = encryptkey;
		
		System.out.println("加密后的请求数据:\n" + new Req().toString());
	}
	
	public static void server() throws Exception {
		Req.data = "XLY1uuY9JAy/lrRgdfxmE6YtN948Rkng67GU5bwzToyL1WO9uy8HLcIaW6i/LszN8iAh7LzjEEllZMoqZg5lfDknlbmv2SJFMnn03knKrKxJlLYR+Z0BYYG9FaSU8Fh1SJ5nimHaMcoDL9ctTFE3R0Rqxua0dpNKwpIxgEqQB78ct6EBsCtyG4Y4SEgzU8/u8H08qiBXgjywiMMMQpfpyM8Y8tuzA/w0keYe7iiqUoQOKHj5CXIPEEobsMgKwzDparb9OPi57PwBgssXwKDBJRmHNEfBRvjw3imb0roZKj2UqY7tvTaEFKz175w1Le7u";
		Req.encryptkey = "XvgDOJ7kXJvTt31DEJtAWxzCTO1xo+U1dZkhZp6yqLZVTDAY8zt+w07pzLmvJ0Ohr1pDL2uChXaQaTVVGPJTp4fZNV88keMKozWxxACnR7Cp/M39aO3EeSykMLqYWiWbkIVT88TCuj42CU/Tigq6GG69MgddjKmjA42RNmltF6M=";
		String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDM3RkuNWkZzxSbsqYP8mcKklebLrxtoebp3JDAoN6tYsRO5X4TmYr32wrphkpQwMQscbt4LjVx4Vj4JPBj/EsTcF8ExvWuEVKBrlQ25Ad7KjBVJtKX9wuKeqUC55dvCr5Aocqyde+IjxCoJrLvU+cRhZ+NXDAqVcr9MQSCHBN2qwIDAQAB";
		String serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbNojYr8KlqKD/y"+
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
		// 验签
		boolean passSign = EncryUtil.checkDecryptAndSign(Req.data,
					Req.encryptkey, clientPublicKey, serverPrivateKey);
		
		if(passSign){
			// 验签通过
			String aeskey = RSA.decrypt(Req.encryptkey,
						serverPrivateKey);
			String data = AES.decryptFromBase64(Req.data,
					aeskey);
			
			JSONObject jsonObj = JSONObject.parseObject(data);
			String userid = jsonObj.getString("userid");
			String phone = jsonObj.getString("phone");
			String randomNumber = jsonObj.getString("randomNumber");
			String timeStamp = jsonObj.getString("timeStamp");
			
			System.out.println("解密后的明文:randomNumber:"+randomNumber+" timeStamp:"+timeStamp);
			
		}else{
			System.out.println("验签失败");
		}
	}
	
	static class Req{
		public static String data;
		public static String encryptkey;
		
		@Override
		public String toString() {
			return "data:"+data+"\nencryptkey:"+encryptkey;
		}
	}
}
