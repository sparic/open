package cn.muye.user.domain;

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

	private boolean passwordChangeRequired = false;

	private boolean receiveEmail = true;

	private boolean registered = false;

	private boolean deactivated = false;  //账号是否无效

	private Date lastVisit;  //最新登录时间

	private Long userRoleId; //详见UserType(对应角色ID)

	private String appKey;

	private String appSecret;

	private String phone;

	private String company;

	private Integer level; //用户等级(1. 金牌ISV，2. 白金ISV，3. 钻石ISV)

	private String url; //申请代理商文件的url返回

	private String description;

	private Boolean isAdminUser;

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

	public boolean isPasswordChangeRequired() {
		return passwordChangeRequired;
	}

	public void setPasswordChangeRequired(boolean passwordChangeRequired) {
		this.passwordChangeRequired = passwordChangeRequired;
	}

	public boolean isReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public boolean isDeactivated() {
		return deactivated;
	}

	public void setDeactivated(boolean deactivated) {
		this.deactivated = deactivated;
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
}
