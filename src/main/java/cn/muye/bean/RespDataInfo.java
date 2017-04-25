package cn.muye.bean;

public class RespDataInfo {

    private String data;//随机数

    private String encryptKey;//时间戳

    public RespDataInfo(){

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }
}