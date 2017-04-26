package cn.muye.model;

import java.util.Date;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
public class File {

    private Long id;

    private String name;

    private String url;

    private String description;

    //源文件名
    private String sourceName;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
