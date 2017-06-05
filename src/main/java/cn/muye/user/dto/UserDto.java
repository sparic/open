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

    private String roleId;

    private String roleName;

    private String level;

    private String levelName;

//    private Boolean haveRight;

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

//    public Boolean getHaveRight() {
//        return haveRight;
//    }
//
//    public void setHaveRight(Boolean haveRight) {
//        this.haveRight = haveRight;
//    }
}
