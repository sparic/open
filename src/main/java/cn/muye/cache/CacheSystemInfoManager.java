package cn.muye.cache;

import org.apache.log4j.Logger;

public class CacheSystemInfoManager {

	protected final static Logger logger = Logger.getLogger(CacheSystemInfoManager.class);
	
	/** publicInfo的缓存 */
	private static ConcurrentHashMapCache<String, CachePublicInfo> publicInfoCache = new ConcurrentHashMapCache<String, CachePublicInfo>();

	/** AccessKeyInfo的缓存 */
	private static ConcurrentHashMapCache<String, String> accessKeyInfoCache = new ConcurrentHashMapCache<String, String>();

	static {

		// publicInfo对象缓存的最大生存时间，单位毫秒，永久保存
		publicInfoCache.setMaxLifeTime(0);

		accessKeyInfoCache.setMaxLifeTime(0);

	}

	private CacheSystemInfoManager() {

	}

	public static void removePublicInfoCache(String accessKey) {
		publicInfoCache.remove(accessKey);
	}

	/**
	 * CachePublicInfo
	 */
	public static CachePublicInfo getPublicInfoCache(String accessKey) {
		if (accessKey == null) {
			return null;
		}
		CachePublicInfo systemInfo = publicInfoCache.get(accessKey);
		if (systemInfo == null) {
			return null;
		}
		return systemInfo;
	}

	public static void insertPublicInfoCache(String accessKey, CachePublicInfo info){
		publicInfoCache.put(accessKey, info);
	}


	/**
	 * CacheAccessKeyInfo
	 */
	public static String getAccessKeyCache(String accessKey) {
		if (accessKey == null) {
			return null;
		}
		String secretKey = accessKeyInfoCache.get(accessKey);
		if (accessKey == null) {
			return null;
		}
		return secretKey;
	}

	public static void insertAccessKeyInfoCache(String accessKey, String secretKey){
		accessKeyInfoCache.put(accessKey, secretKey);
	}

}
