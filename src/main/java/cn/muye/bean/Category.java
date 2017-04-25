package cn.muye.bean;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
public class Category {

    private Long id;

    private Long pid;

    private String name;

    private Long sdkId;

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
}
