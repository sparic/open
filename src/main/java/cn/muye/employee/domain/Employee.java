package cn.muye.employee.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Ray.fu on 2018/3/5.
 *
 *  员工表
 */
@ApiModel(value= "cn.muye.employee.domain.Employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 8731797334639062714L;

    private Long id;

    @ApiModelProperty(value = "公司业务ID")
    private String userBizId; //公司业务ID（对应用户表的bizId）

    @ApiModelProperty(value = "姓名")
    private String name; //姓名

    @ApiModelProperty(value = "公司名称")
    private String companyName; //公司名称

    @ApiModelProperty(value = "手机")
    private String phone; //手机

    @ApiModelProperty(value = "邮箱")
    private String email; //邮箱

    @ApiModelProperty(value = "职位")
    private String position; //职位

    @ApiModelProperty(value = "类别(1-诺亚，2-人形)")
    private Integer type; //类别(1-诺亚，2-人形)

    @ApiModelProperty(value = "是否有效(1-有效，0-无效)")
    private Integer isValid = 1; //是否有效(1-有效，0-无效)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserBizId() {
        return userBizId;
    }

    public void setUserBizId(String userBizId) {
        this.userBizId = userBizId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
