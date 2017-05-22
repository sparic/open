package cn.muye.version.domain;

import java.util.Date;

/**
 * Created by Ray.Fu on 2017/5/2.
 */
public class Version {
    private Long id;

    private String versionCode;

    private String description;

    private Date createTime;

    private Date updateTime;

    private String extendedVersionCode;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtendedVersionCode() {
        return extendedVersionCode;
    }

    public void setExtendedVersionCode(String extendedVersionCode) {
        this.extendedVersionCode = extendedVersionCode;
    }
}
