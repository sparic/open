package cn.muye.cache;

/**
 * Created by enva on 2017/1/23.
 */
public class CacheAccessKeyInfo {

    private CacheAccessKeyInfo(){

    }

    public CacheAccessKeyInfo(String accessKey, String secretKey){
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private String accessKey;//应用ID

    private String secretKey;//密钥

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
