package cn.muye.cooperation.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
public class IsvApply implements Serializable {

    private static final long serialVersionUID = 8267287085026143517L;

    private Long id;

    private Long userId;

    private String url;

    //ISV申请备注
    private String description;

    //审核状态
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
