package cn.muye.train.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ray.Fu on 2018/3/7.
 * 课程表
 */
@ApiModel(value= "cn.muye.train.domain.Train")
public class Train implements Serializable {

    private static final long serialVersionUID = 8731797334639062714L;

    private Long id; //主键ID（自增）

    @ApiModelProperty(value = "议题")
    private String topic; //议题

    @ApiModelProperty(value = "时间")
    private String time; //时间

    @ApiModelProperty(value = "讲师")
    private String teacher; //讲师

    @ApiModelProperty(value = "描述")
    private String description; //描述

    @ApiModelProperty(value = "地点")
    private String location; //地点

    @ApiModelProperty(value = "webex会议地址")
    private String webexUrl; //webex会议地址

    @ApiModelProperty(value = "状态[0-未发送邀请，1-已发送邀请]")
    private Integer status = 0; //状态[0-未发送邀请，1-已发送邀请]

    @ApiModelProperty(value = "员工ID拼接的串")
    private String employeeIds; //员工ID拼接的串

    @ApiModelProperty(value = "邮件主题")
    private String mailSubject; //邮件主题

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;//创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebexUrl() {
        return webexUrl;
    }

    public void setWebexUrl(String webexUrl) {
        this.webexUrl = webexUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(String employeeIds) {
        this.employeeIds = employeeIds;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
