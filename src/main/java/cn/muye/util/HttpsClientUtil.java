package cn.muye.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @see =====================================================================================================
 * @author
 */
public class HttpsClientUtil {
    private static Logger logger = Logger.getLogger(HttpsClientUtil.class);

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
//    public static void main(String[] args)throws Exception{
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("token", "QpN9RAOpo1OK54JsXoYPPK0gpdAZzKRfm2TzUqZX:YB3DUgj4pV7de2Bqqdg5sN8P7Ck=:eyJkZWFkbGluZSI6MTQ4NjY5MTc1N30=");
//        params.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmzaI2K/Cpaig/8gjne0F7t3uE7Kx3eLM914A2FhGWYGKiI1Q3KXJ5c7cKJoIzI/cUsXacgnPORjhmtJb2tXzWaRuil2EtcbhWvRuxUW2gAqAi99U0b/LeEzZH0IUO3tku2ZLaslHj1mC5c7vIceY13a4hK0SMdY1itatTXhHdzQIDAQAB");
//        sendSSLPostRequest("https://localhost:8443/getServerKey", params);
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("method", "DQ_HQ_HNAN_GroupReferQuery");
//        params.put("format", "json");
//        params.put("appId", "502047");
//        params.put("status", "1");
//        params.put("timestamp", "20160324150325");
//        params.put("PROVINCE_CODE", "HNAN");
//        params.put("TRADE_DEPART_PASSWD", "HNWC09");
//        params.put("TRADE_STAFF_ID", "ITFWC000");
//        params.put("TRADE_EPARCHY_CODE", "0731");
//        params.put("TRADE_CITY_CODE", "0000");
//        params.put("TRADE_DEPART_ID", "00302");
//        params.put("ROUTE_EPARCHY_CODE", "0000");
//        params.put("IN_MODE_CODE", "3");
//        params.put("TRADE_TERMINAL_ID", "10.154.92.35");
//        params.put("flowId", "10002261");
//        sendSSLPostRequest("https://111.8.20.250:19080/oppf", params);
//        sendSSLPostRequest("https://111.8.20.248:9443/aopdev/zui/advertisePage.jsp", params);
//    }


    /**
     * 获得KeyStore.
     * @param keyStorePath
     *            密钥库路径
     * @param password
     *            密码
     * @return 密钥库
     * @throws Exception
     */
    public static KeyStore getKeyStore(String password, String keyStorePath)
            throws Exception {
        // 实例化密钥库 KeyStore用于存放证书，创建对象时 指定交换数字证书的加密标准
        //指定交换数字证书的加密标准
//        KeyStore ks = KeyStore.getInstance("JKS");
        KeyStore ks = KeyStore.getInstance("PKCS12");
//        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        // 获得密钥库文件流
        FileInputStream is = new FileInputStream(keyStorePath);
        // 加载密钥库
        ks.load(is, password.toCharArray());
        // 关闭密钥库文件流
        is.close();
        return ks;
    }



    public static KeyStore getTrustKeyStore(String password, String trustKeyStorePath)
            throws Exception {
        // 实例化密钥库 KeyStore用于存放证书，创建对象时 指定交换数字证书的加密标准
        //指定交换数字证书的加密标准
//        KeyStore ks = KeyStore.getInstance("JKS");
        KeyStore ks = KeyStore.getInstance("PKCS12");
//        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        // 获得密钥库文件流
        FileInputStream is = new FileInputStream(trustKeyStorePath);
        // 加载密钥库
        ks.load(is, password.toCharArray());
        // 关闭密钥库文件流
        is.close();
        return ks;
    }

    /**
     * 向HTTPS地址发送POST请求
     * @param reqURL 请求地址 
     * @param body 请求参数
     * @return 响应内容
     */
    public static String sendSSLPostRequestJson(String reqURL, String body){
        long responseLength = 0;                         //响应长度   
        String responseContent = "";                     //响应内容   
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例
        //////////////忽略验证双向通道，开始------------------------------------------------------------
//        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
//            public X509Certificate[] getAcceptedIssuers() { return null; }
//        };
        ////////////////////////忽略验证双向通道，结束---------------------------------------------
        try {

            //////////////////需要验证双向通道，开始///////////////////////////////////////////////////////////////
            // 密码
            String password = "123456";
            // 密钥库
//            String keyStorePath = "F:\\myee\\cer_test\\ca.crt";
//            String keyStorePath = "C:\\Users\\enva\\client.p12";
//            String keyStorePath = "F:\\myee\\163nginx\\client.p12";
//            String keyStorePath = "F:\\myee\\76nginx\\client.p12";//本地
//             String keyStorePath = "F:\\myee\\76nginx\\aopClient.p12";
//            String keyStorePath = "F:\\myee\\document\\aopClient.p12";
//            String keyStorePath = "/opt/cer/client.p12";
              String keyStorePath = "/home/cert_home/aopClient.p12";//正式机
           //信任库
//            String trustStorePath = "F:\\myee\\cer_test\\server.key";
//            String trustStorePath = "C:\\Users\\enva\\client.jks";
//            String trustStorePath = "F:\\myee\\163nginx\\ca.p12";
//            String trustStorePath = "F:\\myee\\76nginx\\ca.p12";//本地
//            String trustStorePath = "F:\\myee\\document\\ca.p12";
//            String trustStorePath = "F:\\myee\\document\\ca.p12";
//            String trustStorePath = "/opt/cer/client.jks";
            String trustStorePath = "/home/cert_home/ca.p12";//正式机
            // 实例化密钥库   KeyManager选择证书证明自己的身份
            KeyManagerFactory keyManagerFactory = KeyManagerFactory
                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
            // 获得密钥库


                KeyStore keyStore = getKeyStore(password, keyStorePath);

                // 初始化密钥工厂
                keyManagerFactory.init(keyStore, password.toCharArray());

                // 实例化信任库    TrustManager决定是否信任对方的证书
                TrustManagerFactory trustManagerFactory = TrustManagerFactory
                        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
                // 获得信任库
                KeyStore trustStore = getTrustKeyStore(password, trustStorePath);
                // 初始化信任库
                trustManagerFactory.init(trustStore);

                // 实例化SSL上下文
                SSLContext ctx = SSLContext.getInstance("TLS");
                // 初始化SSL上下文
                ctx.init(keyManagerFactory.getKeyManagers(),
                        trustManagerFactory.getTrustManagers(), null);

            ///////////////////////////////需要验证双向通道，结束////////////////////////////////////////////////


            /////////忽略验证双向通道，开始------------------------------------------------------------
//            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
//            SSLContext ctx = SSLContext.getInstance("TLS");
//
//            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
//            ctx.init(null, new TrustManager[]{xtm}, null);
            ////////////////////////忽略验证双向通道，结束---------------------------------------------


            //创建SSLSocketFactory 且 不校验域名 
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            //*********不校验域名(该方法已过期，现在直接在SSLSocketFactory构造函数中设置参数，如上行代码所示)*********
            //socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);   

            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上   
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

            HttpPost httpPost = new HttpPost(reqURL);                        //创建HttpPost
//            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //构建POST请求的表单参数
//            for(Map.Entry<String,String> entry : params.entrySet()){
//                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));

//            String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
//            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

            StringEntity se = new StringEntity(body,
                    APPLICATION_JSON, "UTF-8");
//            se.setContentType(CONTENT_TYPE_TEXT_JSON);
//            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);

            httpPost.addHeader("APICode", "YUNN_UNHQ_QUERYOWEFEE");
            httpPost.addHeader("Domain", "COP");
            httpPost.addHeader("RouteValue", "871");


            HttpResponse response = httpClient.execute(httpPost); //执行POST请求   
            HttpEntity entity = response.getEntity();             //获取响应实体   

            if (null != entity) {
                responseLength = entity.getContentLength();
                responseContent = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity); //Consume response content   
            }
            System.out.println("请求地址: " + httpPost.getURI());
            System.out.println("响应状态: " + response.getStatusLine());
            System.out.println("响应长度: " + responseLength);
            System.out.println("响应内容: " + responseContent);
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
            e.printStackTrace();
        } catch (ParseException e) {
            logger.error("ParseException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Exception", e);
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源   
            httpClient = null;
        }
        return responseContent;
    }




    /**
     * 向HTTPS地址发送POST请求
     * @param reqURL 请求地址
     * @param params 请求参数
     * @return 响应内容
     */
    public static String sendSSLPostRequestParams(String reqURL, Map<String, String> params){
        long responseLength = 0;                         //响应长度
        String responseContent = "";                     //响应内容
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例
        //////////////忽略验证双向通道，开始------------------------------------------------------------
        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return null; }
        };
        ////////////////////////忽略验证双向通道，结束---------------------------------------------
        try {

            //////////////////需要验证双向通道，开始///////////////////////////////////////////////////////////////
//            // 密码
//            String password = "123456";
//            // 密钥库
////            String keyStorePath = "F:\\myee\\cer_test\\ca.crt";
////            String keyStorePath = "C:\\Users\\enva\\client.p12";
////            String keyStorePath = "F:\\myee\\163nginx\\client.p12";
////            String keyStorePath = "F:\\myee\\76nginx\\client.p12";//本地
////            String keyStorePath = "F:\\myee\\document\\aopClient.p12";
//            String keyStorePath = "F:\\myee\\document\\aopClient.p12";
////            String keyStorePath = "/opt/cer/client.p12";
////            String keyStorePath = "/home/cert_home/aopClient.p12";//正式机
//            //信任库
////            String trustStorePath = "F:\\myee\\cer_test\\server.key";
////            String trustStorePath = "C:\\Users\\enva\\client.jks";
////            String trustStorePath = "F:\\myee\\163nginx\\ca.p12";
////            String trustStorePath = "F:\\myee\\76nginx\\ca.p12";//本地
////            String trustStorePath = "F:\\myee\\document\\ca.p12";
//            String trustStorePath = "F:\\myee\\document\\ca.p12";
////            String trustStorePath = "/opt/cer/client.jks";
////            String trustStorePath = "/home/cert_home/ca.p12";//正式机
//            // 实例化密钥库   KeyManager选择证书证明自己的身份
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory
//                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            // 获得密钥库
//
//
//            KeyStore keyStore = getKeyStore(password, keyStorePath);
//
//            // 初始化密钥工厂
//            keyManagerFactory.init(keyStore, password.toCharArray());
//
//            // 实例化信任库    TrustManager决定是否信任对方的证书
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory
//                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            // 获得信任库
//            KeyStore trustStore = getTrustKeyStore(password, trustStorePath);
//            // 初始化信任库
//            trustManagerFactory.init(trustStore);
//
//            // 实例化SSL上下文
//            SSLContext ctx = SSLContext.getInstance("TLS");
//            // 初始化SSL上下文
//            ctx.init(keyManagerFactory.getKeyManagers(),
//                    trustManagerFactory.getTrustManagers(), null);

            ///////////////////////////////需要验证双向通道，结束////////////////////////////////////////////////


            /////////忽略验证双向通道，开始------------------------------------------------------------
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");

            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[]{xtm}, null);
            ////////////////////////忽略验证双向通道，结束---------------------------------------------


            //创建SSLSocketFactory 且 不校验域名
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            //*********不校验域名(该方法已过期，现在直接在SSLSocketFactory构造函数中设置参数，如上行代码所示)*********
            //socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

            HttpPost httpPost = new HttpPost(reqURL);                        //创建HttpPost
            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //构建POST请求的表单参数
            for(Map.Entry<String,String> entry : params.entrySet()){
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));

//            String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
//            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

//            StringEntity se = new StringEntity(body,
//                    APPLICATION_JSON, "UTF-8");
//            se.setContentType(CONTENT_TYPE_TEXT_JSON);
//            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
//            httpPost.setEntity(se);

//            httpPost.addHeader("APICode", "YUNN_UNHQ_QUERYOWEFEE");
//            httpPost.addHeader("Domain", "COP");
//            httpPost.addHeader("RouteValue", "871");


            HttpResponse response = httpClient.execute(httpPost); //执行POST请求
            HttpEntity entity = response.getEntity();             //获取响应实体

            if (null != entity) {
                responseLength = entity.getContentLength();
                responseContent = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity); //Consume response content
            }
            System.out.println("请求地址: " + httpPost.getURI());
            System.out.println("响应状态: " + response.getStatusLine());
            System.out.println("响应长度: " + responseLength);
            System.out.println("响应内容: " + responseContent);
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
            e.printStackTrace();
        } catch (ParseException e) {
            logger.error("ParseException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Exception", e);
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源
            httpClient = null;
        }
        return responseContent;
    }


}  
