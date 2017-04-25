package cn.muye.bean;

/**
 * Created by enva on 2016/3/30.
 */
public class RequestJson {

//    private String method;
//
//    private String format;
//
//    private String status;
//
//    private String appId;
//
//    private String timestamp;
//
//    private String flowId;

    private String question;

    private String platform;

    private String userId;

    public RequestJson(){

    }

//    public RequestJson(String method,
//                       String format,
//                       String status,
//                       String appId,
//                       String timestamp,
//                       String flowId,
//                       String question,
//                       String platform,
//                       String userId){
//
//    }

    public RequestJson(String question,
                       String platform,
                       String userId){
        this.question = question;
        this.platform = platform;
        this.userId = userId;
    }

//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
//    }
//
//    public String getFormat() {
//        return format;
//    }
//
//    public void setFormat(String format) {
//        this.format = format;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getAppId() {
//        return appId;
//    }
//
//    public void setAppId(String appId) {
//        this.appId = appId;
//    }
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getFlowId() {
//        return flowId;
//    }
//
//    public void setFlowId(String flowId) {
//        this.flowId = flowId;
//    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}