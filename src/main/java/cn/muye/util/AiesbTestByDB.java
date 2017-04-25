package cn.muye.util;

import cn.muye.bean.RequestJson;
import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AiesbTestByDB
{
	private static final transient Log log = LogFactory
			.getLog(AiesbTestByDB.class);

	private PostMethod method;

	HttpClient httpClient = new HttpClient();

//	public static void main(String[] args) throws Exception
//	{
//		for(int i=0;i < 1; i++)
//		{
//
//			AiesbTestByDB tt = new AiesbTestByDB();
//			tt.testOAuth("");
//		}
//		//tt.test();
//	}

	public Map testOAuth(String code) throws Exception
	{
		InputStream is = null;
		Properties dbProps = new Properties();
		try
		{
			URL url = getClass().getResource("");
			System.out.println(url);

//			is = getClass().getResourceAsStream("/testOAuth.properties");
//			dbProps.load(is);

			String appId = dbProps.getProperty("appId");
			String redirectUri = dbProps.getProperty("redirectUri");
			String appkey = dbProps.getProperty("appkey");
			String grant_type = dbProps.getProperty("grant_type");
			String rsaPublicKey = dbProps.getProperty("rsaPublicKey");
			String status = dbProps.getProperty("status");
			String abilityCode = dbProps.getProperty("abilityCode");

			String format = dbProps.getProperty("format");
			String content = dbProps.getProperty("content");
			String aiesbUrl = dbProps.getProperty("aiesbUrl");
			String publicParam = dbProps.getProperty("publicParam");

			System.out.println("appId=" + appId);
			System.out.println("redirectUri=" + redirectUri);
			System.out.println("appkey=" + appkey);
			System.out.println("grant_type=" + grant_type);
			System.out.println("rsaPublicKey=" + rsaPublicKey);
			System.out.println("status=" + status);
			System.out.println("abilityCode=" + abilityCode);

			System.out.println("format=" + format);
			System.out.println("content=" + content);
			System.out.println("aiesbUrl=" + aiesbUrl);
			System.out.println("publicParam=" + publicParam);

			return testOAuth(code, appId, redirectUri, appkey,
					grant_type, rsaPublicKey, status, abilityCode, format,
					content, aiesbUrl, publicParam);

		}
		catch (Exception e)
		{

			log.error(e.getMessage());
			System.out.println("--------" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public Map testOAuth(String code, String appId, String redirectUri,
						 String appkey, String grant_type, String rsaPublicKey,
						 String status, String abilityCode, String formatType,
						 String content, String aiesbUrl, String publicParam)
			throws Exception
	{
		try
		{
			Map map = new HashMap();

			String md5Sign = "method=" + abilityCode + "&format=" + formatType
					+ "&appId=" + appId + publicParam + "&status=" + status
					// + "&appKey=" + appkey
					+ "&content=" + content;

			// 公共参数
			publicParam = "method=XIAOI_HQ_HNAN_DoAsk&format=json&appId=506034&status=1&timestamp="+DateUtil.getTimeStamp()+"&flowId="+DateUtil.getTimeRandom();
//			String requestUrl = "https://111.8.20.248:9091/oppf?";
			String requestUrl = "http://111.8.20.248:9001/oppf?";
			String[] keyValueArray = publicParam.split("&");
			String format = "json";
			for (int i = 0; i < keyValueArray.length; i++)
			{
				if (null == keyValueArray[i])
				{
					continue;
				}
				String[] array = keyValueArray[i].split("=");
				if (array.length == 2)
				{
					requestUrl += array[0];
					requestUrl += "=";
					requestUrl += URLEncoder.encode(array[1], "UTF-8");
					if (array[0].equals("format"))
					{
						format = array[1];
					}
				}
				else
				if (array.length == 1)
				{
					requestUrl += array[0];
					requestUrl += "=";
				}
				else
				{
					continue;
				}
				if (i != keyValueArray.length - 1)
				{
					requestUrl += "&";
				}
			}

			method = new PostMethod(requestUrl);


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


			String body = json;
			System.out.println("--------------业务参数111111111111----------------" + json);
			if (null != body)
			{
				RequestEntity entity = new StringRequestEntity(body,
						"application/" + format, "UTF-8");
				method.setRequestEntity(entity);
			}


			method.setRequestHeader("APICode", "YUNN_UNHQ_QUERYOWEFEE");
			method.setRequestHeader("Domain", "COP");
			method.setRequestHeader("RouteValue", "871");

			System.out
					.println("--------------访问应用地址-------------" + requestUrl);
			System.out.println("--------------业务参数-----------------" + body);
			System.out
					.println("--------------转换后的公共参数----------" + publicParam);
			log.debug("--------------访问应用地址-------------" + requestUrl);
			log.debug("--------------业务参数-----------------" + body);
			log.debug("--------------公共参数-----------------" + md5Sign);
			log.debug("--------------转换后的公共参数----------" + publicParam);
			long startTime = System.currentTimeMillis();
			int statusCode = httpClient.executeMethod(method);

			long endTime = System.currentTimeMillis();
			if (statusCode != HttpStatus.SC_OK)
			{
				throw new Exception("Method failed:" + method.getStatusLine());
			}

			Header[] headers = method.getRequestHeaders();
			for(int i = 0; i < headers.length; i++)
			{
				System.out.println(headers[i].getName()+"@@"+headers[i].getValue());
			}


			byte[] responseBody = method.getResponseBody();
			String response = new String(responseBody, "UTF-8");
			if (log.isDebugEnabled())
			{
				log.debug("应答结果为：" + response);
				log.debug("应答时间为：" + (endTime - startTime));
			}

			System.out.println(response.toString());

			Map returnMap = new HashMap();

			returnMap.put("publicParam", publicParam);
			returnMap.put("body", body);
			returnMap.put("response", response);

			System.out.println(returnMap.toString());
			return returnMap;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			if (method != null)
			{
				method.releaseConnection();
			}
		}

		return null;
	}

	public static void test() throws Exception {
		URL url = new URL("www.baidu.com");    //把字符串转换为URL请求地址
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestProperty("user", "user");

		conn.connect();// 连接会话
		// 获取输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
		}
		br.close();// 关闭流
		conn.disconnect();// 断开连接
		System.out.println(sb.toString());
	}
}
