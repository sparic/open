package cn.muye.core.enums;

/**
 * Created by Ray.Fu on 2017/5/27.
 */
public enum AgentLevelType {

    AGENT(0, "代理商"),GOLD(1, "金牌ISV"), PLATINUM(2, "白金ISV"), DIAMOND(3, "钻石ISV");

    private Integer value;
    private String name;

    private AgentLevelType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
