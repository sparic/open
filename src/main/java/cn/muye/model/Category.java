package cn.muye.model;

import java.util.Date;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
public class Category {

    private Long id;

    private Long pid;

    private String name;

    private Long sdkId;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSdkId() {
        return sdkId;
    }

    public void setSdkId(Long sdkId) {
        this.sdkId = sdkId;
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
}
