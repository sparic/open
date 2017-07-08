package cn.muye.core.enums;

/**
 *
 */
public enum ApplyStatusType {

    SUBMIT("已提交", 0), AUDITING("待审核", 1), SUCCESS("通过", 2), FAILED("失败", 3);

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
