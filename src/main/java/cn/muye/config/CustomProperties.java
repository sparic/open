package cn.muye.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/3.
 */
@Component
@ConfigurationProperties(prefix="devCenter")
public class CustomProperties {

    private String pushDirs;

    private String pushHttp;

	private long tokenExpireTime;

	private String redisMasterAddress;

	private int redisMasterPort;

	private String redisRedissonCache;

	private String rootAddress;

    private List<String> sdkSupportedTypes;

	private List<String> supportFileTypes;

	public String getPushDirs() {
		return pushDirs;
	}

	public void setPushDirs(String pushDirs) {
		this.pushDirs = pushDirs;
	}

	public String getPushHttp() {
		return pushHttp;
	}

	public void setPushHttp(String pushHttp) {
		this.pushHttp = pushHttp;
	}

	public long getTokenExpireTime() {
		return tokenExpireTime;
	}

	public void setTokenExpireTime(long tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}

	public String getRedisMasterAddress() {
		return redisMasterAddress;
	}

	public void setRedisMasterAddress(String redisMasterAddress) {
		this.redisMasterAddress = redisMasterAddress;
	}

	public int getRedisMasterPort() {
		return redisMasterPort;
	}

	public void setRedisMasterPort(int redisMasterPort) {
		this.redisMasterPort = redisMasterPort;
	}

	public String getRedisRedissonCache() {
		return redisRedissonCache;
	}

	public void setRedisRedissonCache(String redisRedissonCache) {
		this.redisRedissonCache = redisRedissonCache;
	}

	public List<String> getSdkSupportedTypes() {
		return sdkSupportedTypes;
	}

	public void setSdkSupportedTypes(List<String> sdkSupportedTypes) {
		this.sdkSupportedTypes = sdkSupportedTypes;
	}

	public List<String> getSupportFileTypes() {
		return supportFileTypes;
	}

	public void setSupportFileTypes(List<String> supportFileTypes) {
		this.supportFileTypes = supportFileTypes;
	}

	public String getRootAddress() {
		return rootAddress;
	}

	public void setRootAddress(String rootAddress) {
		this.rootAddress = rootAddress;
	}
}
