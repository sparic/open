package cn.muye.user.controller;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.service.AgentApplyService;
import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.core.enums.AgentLevelType;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.shiro.domain.Role;
import cn.muye.shiro.service.ShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.dto.UserDto;
import cn.muye.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private AgentApplyService agentApplyService;

    private static final Integer STATUS_SUBMIT = 0; //已提交
    private static final Integer STATUS_AUDITING = 1; //待审核
    private static final Integer STATUS_SUCCESS = 2; //成功
    private static final Integer STATUS_FAILED = 3; //失败

    private static final Integer GOLD_AGENT = 1;
    private static final Integer PLATINUM_AGENT = 2;
    private static final Integer DIAMOND_AGENT = 3;

    /**
     * 较验用户名
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = {"admin/user/validate", "user/validate"}, method = RequestMethod.GET)
    @ApiOperation(value = "校验用户名", httpMethod = "GET", notes = "校验用户名")
    public AjaxResult validateUserName(@ApiParam(value = "用户名") @RequestParam(value = "userName") String userName) {
        User user = userService.getUserByName(userName);
        if (null != user) {
            return AjaxResult.failed(-1, "用户名已存在!");
        }
        return AjaxResult.success();
    }

    /**
     * 后台添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "admin/user", method = RequestMethod.POST)
    @RequiresPermissions("user:upsert")
    @ApiOperation(value = "后台添加/更新用户", httpMethod = "POST", notes = "后台添加/更新用户")
    public AjaxResult addUser(@ApiParam(value = "用户对象（userType：角色ID）") @RequestBody User user) {
        if (null == user || user != null && (user.getUserName() == null || user.getPassword() == null || user.getEmailAddress() == null || user.getPhone() == null || user.getUrl() == null)) {
            return AjaxResult.failed("用户信息为空");
        }
        User userDb = userService.getUserById(user.getId());
        User sameNameUser = userService.getUserByName(user.getUserName());
        if ((userDb != null && sameNameUser != null && !sameNameUser.getId().equals(userDb.getId())) || userDb == null &&  sameNameUser != null && sameNameUser.getUserName().equals(user.getUserName())) {
            return AjaxResult.failed("用户名已存在");
        }
        if (user.getId() != null && user.getId() >= 1) {
            if (userDb != null) {
                userDb.setPhone(user.getPhone());
                userDb.setLevel(user.getLevel());
                userDb.setEmailAddress(user.getEmailAddress());
                userDb.setPassword(user.getPassword());
                userDb.setUserName(user.getUserName());
                userDb.setDeactivated(user.isDeactivated());
                userDb.setUserRoleId(user.getUserRoleId());
                userService.updateAndBindRole(userDb); //更新用户绑定角色
                return AjaxResult.success(objectToDto(userDb));
            } else {
                return AjaxResult.failed("不存在该用户");
            }
        } else {
            try {
                userService.saveAndBindRole(user); //添加用户 绑定角色 发送邮件
            } catch (Exception e) {
                logger.error("{}", e);
                return AjaxResult.failed("添加失败");
            }
            return AjaxResult.success(objectToDto(user));
        }
    }

    /**
     * 前台添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ApiOperation(value = "添加/更新用户", httpMethod = "POST", notes = "添加/更新用户")
    public AjaxResult addUserFront(@ApiParam(value = "用户对象（userType：角色ID）") @RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        if (null == user || user != null && (user.getUserName() == null || user.getPassword() == null || user.getEmailAddress() == null || user.getPhone() == null || user.getUrl() == null)) {
            return AjaxResult.failed("用户信息为空");
        }
        User userDb = userService.getUserById(user.getId());
        User sameNameUser = userService.getUserByName(user.getUserName());
        if ((userDb != null && sameNameUser != null && !sameNameUser.getId().equals(userDb.getId())) || userDb == null &&  sameNameUser != null && sameNameUser.getUserName().equals(user.getUserName())) {
            return AjaxResult.failed("用户名已存在");
        }
        if (user.getId() != null && user.getId() >= 1) {
            if (userDb != null && userDb.getUserName().equals(userName)) {
                userDb.setPassword(user.getPassword());
                userDb.setPhone(user.getPhone());
                userDb.setEmailAddress(user.getEmailAddress());
                userService.update(userDb); //更新用户
                return AjaxResult.success(objectToDto(userDb));
            } else if (userDb != null && !userDb.getUserName().equals(userName)){
                return AjaxResult.failed("您无权修改他人的个人信息");
            } else {
                return AjaxResult.failed("不存在该用户");
            }
        } else {
            try {
                user.setUserRoleId(Constants.CUSTOMER_ROLE_ID);
                userService.saveAndApplyAgent(user); //添加用户 绑定角色
            } catch (Exception e) {
                logger.error("{}", e);
                return AjaxResult.failed("添加失败");
            }
            return AjaxResult.success(objectToDto(user));
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
        PageHelper.startPage(page, pageSize);
        List<User> userList = userService.getUserList(page);
        List<UserDto> userDtoList = Lists.newArrayList();
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                if (!Constants.SUPER_ADMIN_ID.equals(user.getUserRoleId())) {
                    userDtoList.add(objectToDto(user));
                }
            }
        }
        PageInfo<UserDto> userPageInfo = new PageInfo(userList);
        userPageInfo.setList(userDtoList);
        return AjaxResult.success(userPageInfo);
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "admin/user/{id}", method = RequestMethod.DELETE)
    @RequiresPermissions("user:delete")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE", notes = "删除用户")
    public AjaxResult deleteUser(@ApiParam(value = "用户ID") @PathVariable Long id) {
        if (id != null) {
            User userDb = userService.getUserById(id);
            if (userDb != null) {
                UserDto dto = objectToDto(userDb);
                if (dto.getRoleId().equals(Constants.SUPER_ADMIN_ID)) {
                    return AjaxResult.failed("不能删除超级管理员");
                }
                userDb.setDeactivated(true);
                userService.deleteById(userDb.getId());
                return AjaxResult.success("删除成功");
            }
        } else {
            return AjaxResult.failed("不存在该用户");
        }
        return null;
    }

    @RequestMapping(value = "admin/user/role", method = RequestMethod.POST)
    @RequiresPermissions("user:upsert")
    @ApiOperation(value = "绑定用户角色", httpMethod = "POST", notes = "绑定用户角色")
    public AjaxResult bindUserRole(@ApiParam(value = "角色ID集合,数组类型[]，逗号分隔") @RequestParam Long roleId, @ApiParam(value = "用户ID") @RequestParam Long userId) {
        try {
            shiroService.bindUserRole(roleId, userId);
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            return AjaxResult.failed("绑定失败");
        } finally {
        }
        return AjaxResult.success("绑定成功");
    }

    /**
     * 后台登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = {"admin/login", "/login"}, method = RequestMethod.POST)
    @ApiOperation(value = "前台登录或后台登录", httpMethod = "POST", notes = "前台登录或后台登录")
    public AjaxResult login(@ApiParam(value = "用户对象") @RequestBody User user) {
        return doLogin(user.getUserName(), user.getPassword());
    }

    private AjaxResult doLogin(String userName, String password) {
        UserDto dto = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            User user = userService.getUserByName(userName);
            return AjaxResult.success(objectToDto(user), "成功登录");
        } catch (AuthenticationException e) {
            logger.error("{}", e);
            return AjaxResult.failed("您的账号或密码输入错误");
        } finally {
        }
    }

    @RequestMapping(value = {"admin/user/{userId}"}, method = RequestMethod.GET)
    @RequiresPermissions("user:admin_detail")
    @ApiOperation(value = "后台查看用户详情", httpMethod = "GET", notes = "后台查看用户详情")
    public AjaxResult getUserDetailAdmin(@ApiParam(value = "用户ID")@PathVariable Long userId) {
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        User userDb = userService.getUserById(userId);
        if (userDb != null) {
            if (!userDb.getUserName().equals(userName)) {
                return AjaxResult.failed("您无权查看他人的详情");
            }
            return AjaxResult.success(objectToDto(userDb));
        } else {
            return AjaxResult.failed("不存在的用户");
        }
    }

    @RequestMapping(value = {"user/{userId}"}, method = RequestMethod.GET)
    @ApiOperation(value = "前台查看用户详情", httpMethod = "GET", notes = "前台查看用户详情")
    public AjaxResult getUserDetailFront(@ApiParam(value = "用户ID")@PathVariable Long userId) {
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        User userDb = userService.getUserById(userId);
        if (userDb != null) {
            if (!userDb.getUserName().equals(userName)) {
                return AjaxResult.failed("您无权查看他人的详情");
            }
            return AjaxResult.success(objectToDto(userDb));
        } else {
            return AjaxResult.failed("不存在的用户");
        }
    }

    @RequestMapping(value = {"user/agentApply/{userId}"}, method = RequestMethod.GET)
    @ApiOperation(value = "前台查看代理商申请进展", httpMethod = "GET", notes = "前台查看代理商申请进展")
    public AjaxResult getAgentApplyByUserId(@ApiParam(value = "用户ID")@PathVariable Long userId) {
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        AgentApplyDto agentApplyDto = agentApplyService.getDtoByUserId(userId);
        User userDb = userService.getUserById(userId);
        if (userDb != null) {
            if (!userDb.getUserName().equals(userName)) {
                return AjaxResult.failed("您无权查看他人的代理商申请进展");
            }
            List<Map> result = Lists.newArrayList();
            result = assembleAgentApplyResult(result, agentApplyDto);
            return AjaxResult.success(result);
        } else {
            return AjaxResult.failed("不存在该用户");
        }
    }

    private List<Map> assembleAgentApplyResult(List<Map> result, AgentApplyDto agentApplyDto) {
        //遍历3次总共分3个状态(1. 已提交 2. 待审核 3. 通过/失败)
        for (int i = 0; i < 3; i++) {
            Map map = Maps.newHashMap();
            map.put("flag", (i == Integer.valueOf(agentApplyDto.getStatus()) ? "1" : "0"));
            map.put("description", (i == Integer.valueOf(agentApplyDto.getStatus()) ? agentApplyDto.getDescription() : ""));
            //如果遍历到了通过/失败
            if (i == 2) {
                if (STATUS_SUCCESS == Integer.valueOf(agentApplyDto.getStatus()) || STATUS_FAILED == Integer.valueOf(agentApplyDto.getStatus())) {
                    map.put("status", STATUS_SUCCESS != Integer.valueOf(agentApplyDto.getStatus()) && STATUS_FAILED != Integer.valueOf(agentApplyDto.getStatus()) ? "" : agentApplyDto.getStatus());
                }
            }
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = {"admin/user/loginOut", "user/loginOut"}, method = RequestMethod.POST)
    @ApiOperation(value = "注销", httpMethod = "POST", notes = "注销")
    public AjaxResult adminLogOut() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception e) {
            logger.error("{}", e);
            return AjaxResult.failed("注销失败");
        }
        return AjaxResult.success("注销成功");
    }

    private UserDto objectToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setPhone(user.getPhone() == null ? "" : user.getPhone());
        dto.setRoleId(user.getUserRoleId() == null ? "" : String.valueOf(user.getUserRoleId()));
        if (user.getUserRoleId() != null) {
            Role roleDb = shiroService.getById(user.getUserRoleId());
            dto.setRoleName(roleDb.getCnName());
        } else {
            dto.setRoleName("");
        }
        dto.setLevelId(user.getLevel() == null ? "" : String.valueOf(user.getLevel()));
        if (user.getLevel() != null) {
            Integer levelId = user.getLevel();
            String levelName = levelId.equals(GOLD_AGENT) ? AgentLevelType.GOLD.getName() : (levelId.equals(PLATINUM_AGENT) ? AgentLevelType.PLATINUM.getName() : levelId.equals(DIAMOND_AGENT) ? AgentLevelType.DIAMOND.getName() : "");
            dto.setLevelName(levelName);
        } else {
            dto.setLevelName("");
        }
        dto.setEmailAddress(user.getEmailAddress() == null ? "" : user.getEmailAddress());
        dto.setHaveRight(!user.getUserRoleId().equals(Constants.CUSTOMER_ROLE_ID) ? true : false);
        return dto;
    }
}
