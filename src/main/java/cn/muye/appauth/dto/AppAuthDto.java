package cn.muye.appauth.dto;

import java.util.Date;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
public class AppAuthDto {

    private Long id;

    private String appId; //应用ID

    private String startTime; //授权开始时间

    private String endTime; //授权到期时间

    private Long userId; //用户ID

    private Integer snCount; //sn数量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSnCount() {
        return snCount;
    }

    public void setSnCount(Integer snCount) {
        this.snCount = snCount;
    }
}
