package cn.muye.cache;

/**
 * Created by enva on 2017/1/23.
 */
public class CachePublicInfo {
    private String accessKey;//应用ID

    private String clientPublicKey;//客户端公钥

    private String serverPublicKey;//服务器公钥

    private String serverPrivateKey;//服务器私钥

    private String randomNumber;//随机数

    private String timeStamp;//时间戳

    private String token;//客户端生成的token

    private String clientRandomNumber;//客户端随机数

    private String clientTimeStamp;//客户端时间戳

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getClientPublicKey() {
        return clientPublicKey;
    }

    public void setClientPublicKey(String clientPublicKey) {
        this.clientPublicKey = clientPublicKey;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getServerPublicKey() {
        return serverPublicKey;
    }

    public void setServerPublicKey(String serverPublicKey) {
        this.serverPublicKey = serverPublicKey;
    }

    public String getServerPrivateKey() {
        return serverPrivateKey;
    }

    public void setServerPrivateKey(String serverPrivateKey) {
        this.serverPrivateKey = serverPrivateKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientRandomNumber() {
        return clientRandomNumber;
    }

    public void setClientRandomNumber(String clientRandomNumber) {
        this.clientRandomNumber = clientRandomNumber;
    }

    public String getClientTimeStamp() {
        return clientTimeStamp;
    }

    public void setClientTimeStamp(String clientTimeStamp) {
        this.clientTimeStamp = clientTimeStamp;
    }
}
