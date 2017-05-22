//package cn.muye.utils;
//
//import cn.muye.config.CustomProperties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//
///**
// * Created with IntelliJ IDEA.
// * Project Name : devCenter
// * User: Jelynn
// * Date: 2017/5/5
// * Time: 13:35
// * Describe:
// * Version:1.0
// */
//
//public class TokenUtils {
//
//	private static final Logger Log = LoggerFactory.getLogger(TokenUtils.class);
//	private static MessageDigest md5 = null;
//
//	static {
//		try {
//			md5 = MessageDigest.getInstance("MD5");
//		} catch (NoSuchAlgorithmException e) {
//			Log.error("初始化MessageDigest出错", e);
//		}
//	}
//
//	public static String createToken(String userName) {
//		long currTime = System.currentTimeMillis();
//		String str = userName + currTime;
//		byte[] bs = md5.digest(str.getBytes());
//		StringBuilder sb = new StringBuilder(40);
//		for(byte x:bs) {
//			if((x & 0xff)>>4 == 0) {
//				sb.append("0").append(Integer.toHexString(x & 0xff));
//			} else {
//				sb.append(Integer.toHexString(x & 0xff));
//			}
//		}
//		return sb.toString();
//	}
//
//	public static String getUserMD5(String userName, String password) {
//		String str = userName + password;
//		byte[] bs = md5.digest(str.getBytes());
//		StringBuilder sb = new StringBuilder(40);
//		for(byte x:bs) {
//			if((x & 0xff)>>4 == 0) {
//				sb.append("0").append(Integer.toHexString(x & 0xff));
//			} else {
//				sb.append(Integer.toHexString(x & 0xff));
//			}
//		}
//		return sb.toString();
//	}
//
//}
