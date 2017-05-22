//package cn.muye.cache;
//
//import cn.muye.cache.redisson.FastJsonCodec;
//import cn.muye.user.domain.Token;
//import org.redisson.api.RMapCache;
//import org.redisson.api.annotation.REntity;
//import org.redisson.api.annotation.RId;
//
//import java.io.Serializable;
//import java.util.Map;
//
///**
// * Created by Martin on 2016/9/6.
// */
//@REntity
//public class CommonCache implements Serializable {
//	@RId
//	private String envName;
//
//	private Map<String, Token> tokenCache;
//
//	public String getEnvName() {
//		return envName;
//	}
//
//	public void setEnvName(String envName) {
//		this.envName = envName;
//	}
//
//	public Map<String, Token> getTokenCache() {
//		return tokenCache;
//	}
//
//	public void setTokenCache(Map<String, Token> tokenCache) {
//		this.tokenCache = tokenCache;
//	}
//}
