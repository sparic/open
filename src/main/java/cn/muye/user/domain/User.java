package cn.muye.user.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/3
 * Time: 14:38
 * Describe: 用户
 * Version:1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 8731797334639062714L;

	private Long id;

	private String userName;

	private String password;

	private String emailAddress;

	private Integer sex; //性别

	private Boolean passwordChangeRequired = false;

	private Boolean receiveEmail = true;

	private Boolean registered = false;

	private Boolean activated;  //账号是否无效

	private Date lastVisit;  //最新登录时间

	private Long userRoleId; //详见UserType(对应角色ID)

	private String appKey;

	private String appSecret;

	private String phone;

	@ApiModelProperty(value = "公司名称")
	private String company; //公司名称

	private Integer level; //用户等级(1. 金牌ISV，2. 白金ISV，3. 钻石ISV)

	private String url; //申请代理商文件的url返回

	private String description; //描述

	private Boolean isAdminUser; //是否为管理员

	@ApiModelProperty(value = "业务主键(时间戳md5)")
	private String bizId; //业务主键(时间戳md5)

	@ApiModelProperty(value = "销售负责人")
	private String salesMan; //销售负责人

	@ApiModelProperty(value = "类别(1-诺亚，2-人形)")
	private Integer type; //类别(1-诺亚，2-人形)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Boolean getPasswordChangeRequired() {
		return passwordChangeRequired;
	}

	public void setPasswordChangeRequired(Boolean passwordChangeRequired) {
		this.passwordChangeRequired = passwordChangeRequired;
	}

	public Boolean getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(Boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public Boolean getIsAdminUser() {
		return isAdminUser;
	}

	public void setIsAdminUser(Boolean isAdminUser) {
		this.isAdminUser = isAdminUser;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
