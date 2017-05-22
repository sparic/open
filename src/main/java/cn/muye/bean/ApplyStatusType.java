package cn.muye.bean;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/10
 * Time: 10:16
 * Describe:
 * Version:1.0
 */
public enum ApplyStatusType {

    APPLYING("申请中", 0), SUCCESS("申请成功", 1), FAILED("申请失败", 2);

    private String name;
    private Integer value;

    private ApplyStatusType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
