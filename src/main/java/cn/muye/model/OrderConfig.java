package cn.muye.model;

/**
 * Created by Selim on 2017/4/19.
 */
public class OrderConfig {

    private Integer id;

    private String order;

    private String keyWords;

    private String configText;

    private String triggerAction;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTriggerAction() {
        return triggerAction;
    }

    public void setTriggerAction(String triggerAction) {
        this.triggerAction = triggerAction;
    }

    public String getConfigText() {
        return configText;
    }

    public void setConfigText(String configText) {
        this.configText = configText;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
