package cn.muye.appauth.domain;

import java.beans.Transient;
import java.util.Date;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
public class AppAuth {

    private Long id;

    private String appId; //应用ID

    private Date startTime; //授权开始时间

    private Date endTime;  //授权到期时间

    private String snCodeArr; //绑定的SN码

    private Integer authLimit; //授权数量上限

    private Long userId; //用户ID

    private Integer validityPeriod; //有效期(月)

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSnCodeArr() {
        return snCodeArr;
    }

    public void setSnCodeArr(String snCodeArr) {
        this.snCodeArr = snCodeArr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAuthLimit() {
        return authLimit;
    }

    public void setAuthLimit(Integer authLimit) {
        this.authLimit = authLimit;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}
