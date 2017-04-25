package cn.muye.util;

import com.alibaba.fastjson.JSON;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Auth {
	    private final String accessKey;
	    private final SecretKeySpec secretKey;

	    private Auth(String accessKey, SecretKeySpec secretKeySpec) {
	        this.accessKey = accessKey;
	        this.secretKey = secretKeySpec;
	    }

	    public static Auth create(String accessKey, String secretKey) {
	        if(!StringUtils.isNullOrEmpty(accessKey) && !StringUtils.isNullOrEmpty(secretKey)) {
	            byte[] sk = StringUtils.utf8Bytes(secretKey);
	            SecretKeySpec secretKeySpec = new SecretKeySpec(sk, "HmacSHA1");
	            return new Auth(accessKey, secretKeySpec);
	        } else {
	            throw new IllegalArgumentException("empty key");
	        }
	    }

	    private Mac createMac() {
	        try {
	            Mac mac = Mac.getInstance("HmacSHA1");
	            mac.init(this.secretKey);
	            return mac;
	        } catch (GeneralSecurityException var3) {
	            var3.printStackTrace();
	            throw new IllegalArgumentException(var3);
	        }
	    }

	    private String sign(byte[] data) {
	        Mac mac = this.createMac();
	        String encodedSign = new String(Base64.encode(mac.doFinal(data)));
	        return this.accessKey + ":" + encodedSign;
	    }


	    private String signWithData(byte[] data) {
	        String s = new String(Base64.encode(data));
	        return this.sign(StringUtils.utf8Bytes(s)) + ":" + s;
	    }

	    public String sendToken(long expires) {
	    	if(expires <= 0L){
	    		expires = 10L;
	    	}
	    	if(expires > 1800L){
	    		expires = 1800L;
	    	}
	        long deadline = System.currentTimeMillis() / 1000L + expires;
	        return this.sendTokenWithDeadline(deadline);
	    }

	    public String sendTokenWithDeadline(long deadline) {
	        Map<String, Object> x = new HashMap<String, Object>();
	        x.put("deadline", Long.valueOf(deadline));
	        String s = JSON.toJSONString(x);
	        return this.signWithData(StringUtils.utf8Bytes(s));
	    }

}
