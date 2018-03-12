package cn.muye.user.adminApi.controller;

import cn.muye.cooperation.adminApi.service.AdminAgentApplyService;
import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.core.enums.AgentLevelType;
import cn.muye.shiro.domain.Role;
import cn.muye.shiro.service.AdminShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.dto.UserDto;
import cn.muye.user.adminApi.service.AdminUserService;
import cn.muye.utils.MD5Util;
import cn.muye.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/3
 * Time: 14:31
 * Describe:
 * Version:1.0
 */
@RestController
public class AdminUserController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    private static final Boolean ACTIVATED = true;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminShiroService adminShiroService;

    /**
     * 较验用户名
     * @param userName
     * @return
     */
//    @RequestMapping(value = {"admin/user/validate", "user/validate"}, method = RequestMethod.GET)
//    @ApiOperation(value = "校验用户名", httpMethod = "GET", notes = "校验用户名")
//    public AjaxResult validateUserName(@ApiParam(value = "用户名") @RequestParam(value = "userName") String userName) {
//        User user = userService.getUserByName(userName);
//        if (null != user) {
//            return AjaxResult.failed(-1, "用户名已存在!");
//        }
//        return AjaxResult.success();
//    }

    /**
     * 后台添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "admin/user", method = RequestMethod.POST)
    @RequiresPermissions("user:upsert")
    @ApiOperation(value = "后台添加/更新用户", httpMethod = "POST", notes = "后台添加/更新用户")
    public AjaxResult addUserAdmin(@ApiParam(value = "用户对象（userType：角色ID）") @RequestBody User user) {
        try {
            String userName = user.getUserName();
            String emailAddress = user.getEmailAddress();
            String company = user.getCompany();
            String phone = user.getPhone();
            String password = user.getPassword();
            Boolean flag = user.getActivated();
            if (userName == null || StringUtils.isEmpty(emailAddress) ||
                    StringUtils.isEmpty(phone) || StringUtils.isEmpty(company) ||
                    user.getType() == null || StringUtils.isEmpty(user.getSalesMan())) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "用户信息为空或信息不全");
            }
            Long id = user.getId();
            Integer level = user.getLevel();
            User sameNameUser = adminUserService.getByName(userName);
            User sameCompanyUser = adminUserService.getByCompany(company);
            User sameEmailUser = adminUserService.getByEmail(emailAddress);
            if ((sameNameUser != null && !sameNameUser.getId().equals(id)) &&
                    sameNameUser.getUserName().equals(userName)) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "用户名已存在");
            }
            if ((sameCompanyUser != null && !sameCompanyUser.getId().equals(id)) &&
                    sameCompanyUser.getCompany().equals(company)) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "公司已存在");
            }
            if ((sameEmailUser != null && !sameEmailUser.getId().equals(id)) &&
                    sameEmailUser.getEmailAddress().equals(emailAddress)) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "邮箱已注册");
            }
            if (id != null) {
                User userDb = adminUserService.getUserById(id);
                Integer levelDb = userDb.getLevel();
                if (userDb == null) {
                    return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该用户");
                }
                if (levelDb == null) {
                    return AjaxResult.failed("用户等级为空，请先通过审核再修改用户信息");
                }
                if (!levelDb.equals(level)) {
                    return AjaxResult.failed("不能直接修改用户等级");
                }
                userDb.setPhone(phone);
                userDb.setLevel(level);
                userDb.setEmailAddress(emailAddress);
                userDb.setUserName(userName);
                if (flag != null) {
                    userDb.setActivated(flag);
                }
                adminUserService.updateAndBindRole(userDb); //更新用户绑定角色
                return AjaxResult.success(objectToDtoAdmin(userDb), "修改成功");
            } else {
                if (StringUtils.isEmpty(password)) {
                    return AjaxResult.failed("密码不能为空");
                }
                user.setPassword(MD5Util.getMD5String(password));
                user.setUserRoleId(Constants.CUSTOMER_ROLE_ID);
                user.setBizId(MD5Util.getMD5String(String.valueOf(new Date().getTime())));
                user.setActivated(ACTIVATED);
                adminUserService.saveAndBindRole(user); //添加用户 绑定角色 发送邮件
                return AjaxResult.success(objectToDtoAdmin(user), "新增成功");
            }
        } catch (Exception e) {
            logger.error("{}", e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "操作失败");
        } finally {
        }
    }

    /**
     * 列出全部用户
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "admin/user", method = RequestMethod.GET)
    @RequiresPermissions("user:query")
    @ApiOperation(value = "列出全部用户", httpMethod = "GET", notes = "列出全部用户")
    public AjaxResult getUserList(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize, true, null, true);
        List<User> userList = adminUserService.getUserList(page);
        List<UserDto> userDtoList = Lists.newArrayList();
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                if (!Constants.SUPER_ADMIN_ID.equals(user.getUserRoleId())) {
                    userDtoList.add(objectToDtoAdmin(user));
                }
            }
        }
        PageInfo<UserDto> userPageInfo = new PageInfo(userList);
        userPageInfo.setList(userDtoList);
        return AjaxResult.success(userPageInfo, "查询成功");
    }

    /**
     * 禁用用户
     *
     * @return
     */
    @RequestMapping(value = "admin/user/{id}", method = RequestMethod.DELETE)
    @RequiresPermissions("user:deActive")
    @ApiOperation(value = "禁用用户", httpMethod = "DELETE", notes = "禁用用户")
    public AjaxResult deleteUserAdmin(@ApiParam(value = "用户ID") @PathVariable String id) {
        if (id != null) {
            User userDb = adminUserService.getUserById(Long.valueOf(id));
            if (userDb != null) {
                UserDto dto = objectToDtoAdmin(userDb);
                if (dto.getRoleId().equals(Constants.SUPER_ADMIN_ID)) {
                    return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不能禁用超级管理员");
                }
                userDb.setActivated(true);
                adminUserService.deActivateById(userDb.getId());
                return AjaxResult.success(userDb, "禁用成功");
            }
        } else {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该用户");
        }
        return null;
    }

    @RequestMapping(value = "admin/user/role", method = RequestMethod.POST)
    @RequiresPermissions("user:upsert")
    @ApiOperation(value = "绑定用户角色", httpMethod = "POST", notes = "绑定用户角色")
    public AjaxResult bindUserRoleAdmin(@ApiParam(value = "角色ID集合,数组类型[]，逗号分隔") @RequestParam Long roleId,
                                        @ApiParam(value = "用户ID") @RequestParam Long userId) {
        try {
            adminShiroService.bindUserRole(roleId, userId);
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "绑定失败");
        } finally {
        }
        return AjaxResult.success("", "绑定成功");
    }

    /**
     * 后台登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = {"admin/login"}, method = RequestMethod.POST)
    @ApiOperation(value = "后台登录", httpMethod = "POST", notes = "后台登录")
    public AjaxResult adminLogin(@ApiParam(value = "用户对象") @RequestBody User user) {
        if (user != null && user.getUserName() != null && user.getPassword() != null) {
            user.setPassword(MD5Util.getMD5String(user.getPassword()));
            User userDb = adminUserService.checkAdminLogin(user);
            if (userDb != null) {
                return doLoginAdmin(user.getUserName(), user.getPassword());
            } else {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "用户名或密码错误");
            }
        } else {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "用户名或密码为空");
        }
    }

    /**
     * 后台登录
     *
     * @param userName
     * @param password
     * @return
     */
    private AjaxResult doLoginAdmin(String userName, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            User user = adminUserService.getByName(userName);
            return AjaxResult.success(objectToDtoAdmin(user), "成功登录");
        } catch (AuthenticationException e) {
            logger.error("{}", e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您的账号或密码输入错误");
        } finally {
        }
    }

//    @RequestMapping(value = {"admin/user/{userId}"}, method = RequestMethod.GET)
//    @RequiresPermissions("user:admin_detail")
//    @ApiOperation(value = "后台查看用户详情", httpMethod = "GET", notes = "后台查看用户详情")
//    public AjaxResult getUserDetailAdmin(@ApiParam(value = "用户ID") @PathVariable String userId) {
//        if (userId == null || userId.trim().length() == 0) {
//            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
//        }
//        Subject subject = SecurityUtils.getSubject();
//        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
//        User userDb = adminUserService.getUserById(Long.valueOf(userId));
//        if (userDb != null && !userDb.getUserName().equals(userName)) {
//            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您无权查看他人的详情");
//        } else if (userDb != null && userDb.getUserName().equals(userName)) {
//            return AjaxResult.success(objectToDtoAdmin(userDb), "查询成功");
//        } else {
//            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在的用户");
//        }
//    }

    @RequestMapping(value = {"admin/user/loginOut"}, method = RequestMethod.POST)
    @ApiOperation(value = "后台注销", httpMethod = "POST", notes = "后台注销")
    public AjaxResult adminLogOut() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception e) {
            logger.error("{}", e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "注销失败");
        }
        return AjaxResult.success("", "注销成功");
    }

    private UserDto objectToDtoAdmin(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setPhone(user.getPhone() == null ? "" : user.getPhone());
        dto.setRoleId(user.getUserRoleId() == null ? "" : String.valueOf(user.getUserRoleId()));
        dto.setBizId(user.getBizId());
        dto.setCompany(user.getCompany());
        dto.setSalesMan(user.getSalesMan());
        dto.setType(user.getType());
        if (user.getUserRoleId() != null) {
            Role roleDb = adminShiroService.getById(user.getUserRoleId());
            dto.setRoleName(roleDb.getCnName());
        } else {
            dto.setRoleName("");
        }
        dto.setLevel(user.getLevel() == null ? "" : String.valueOf(user.getLevel()));
        if (user.getLevel() != null) {
            Integer levelId = user.getLevel();
            List<String> levelList = new ArrayList<String>(Arrays.asList(AgentLevelType.AGENT.getName(), AgentLevelType.GOLD.getName(), AgentLevelType.PLATINUM.getName(), AgentLevelType.DIAMOND.getName()));
            String levelName = levelList.get(levelId);
            dto.setLevelName(levelName);
        } else {
            dto.setLevelName("");
        }
        dto.setActivated(user.getActivated());
        dto.setEmailAddress(user.getEmailAddress() == null ? "" : user.getEmailAddress());
        return dto;
    }

    /**
     * 更新用户的业务ID，注册用户前先赋值业务ID
     *
     * @return
     */
    @PostMapping(value = "updateUserMd5Key")
    public AjaxResult updateUserMd5Key() {
        List<User> userList = adminUserService.getUserList(null);
        if (userList == null) {
            return AjaxResult.failed("用户列表为空");
        }
        for (User user : userList) {
            user.setBizId(MD5Util.getMD5String(String.valueOf(new Date().getTime())));
            adminUserService.updateUser(user);
        }
        return AjaxResult.success("更新成功");
    }
}
