package cn.muye.version.dto;

import java.util.Date;

/**
 * Created by Ray.Fu on 2017/5/8.
 */
public class VersionDto {

    private Long id;

    private String versionCode;

    private String description;

    private String createTime;

    private String updateTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getExtendedVersionCode() {
        return extendedVersionCode;
    }

    public void setExtendedVersionCode(String extendedVersionCode) {
        this.extendedVersionCode = extendedVersionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
