package cn.muye.appauth.dto;

import java.util.Date;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
public class AuthDto4App {

    private String appId;

    private String endTime;

    private String snCode;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }
}
