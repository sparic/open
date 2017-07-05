package cn.muye.user.dto;

import java.util.Date;
import java.util.Set;

/**
 * Created by Ray.Fu on 2017/5/18.
 */
public class UserDto {

    private Long id;

    private String userName;

    private String password;

    private String emailAddress;

    private String phone;

    private String company;

    private String roleId; //角色ID（1.超级管理员ID 2.普通用户ID(只区分两种，普通用户可以是代理商也可以是ISV)）

    private String roleName; //角色ID（1.超级管理员 2.普通用户(只区分两种，普通用户可以是代理商也可以是ISV)）

    private String level;//用户等级(1. 金牌ISV，2. 白金ISV，3. 钻石ISV)

    private String levelName; //用户等级名称(1. 金牌ISV，2. 白金ISV，3. 钻石ISV)

    private Boolean activated;

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
