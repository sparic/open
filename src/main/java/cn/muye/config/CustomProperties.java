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

    private List<String> excludeFileTypes;

    public String getPushDirs() {
        return pushDirs;
    }

    public void setPushDirs(String pushDirs) {
        this.pushDirs = pushDirs;
    }

    public String getHttp() {
        return pushHttp;
    }

    public void setHttp(String http) {
        this.pushHttp = http;
    }

    public List<String> getExcludeFileTypes() {
        return excludeFileTypes;
    }

    public void setExcludeFileTypes(List<String> excludeFileTypes) {
        this.excludeFileTypes = excludeFileTypes;
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
}
