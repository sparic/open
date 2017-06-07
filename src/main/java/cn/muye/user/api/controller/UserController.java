package cn.muye.user.api.controller;

import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.dto.IsvApplyDto;
import cn.muye.cooperation.api.service.AgentApplyService;
import cn.muye.cooperation.api.service.IsvApplyService;
import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.core.enums.AgentLevelType;
import cn.muye.shiro.domain.Role;
import cn.muye.shiro.service.ShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.dto.UserDto;
import cn.muye.user.api.service.UserService;
import cn.muye.utils.MD5Util;
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
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private IsvApplyService isvApplyService;

    private static final Integer STATUS_SUBMIT = 0; //已提交
    private static final Integer STATUS_AUDITING = 1; //待审核
    private static final Integer STATUS_SUCCESS = 2; //成功
    private static final Integer STATUS_FAILED = 3; //失败

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
     * 前台添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ApiOperation(value = "添加/更新用户", httpMethod = "POST", notes = "添加/更新用户")
    public AjaxResult addUser(@ApiParam(value = "用户对象（userType：角色ID）") @RequestBody User user) {
        if (null == user || user != null && (user.getUserName() == null || user.getPassword() == null || user.getEmailAddress() == null || user.getPhone() == null || user.getUrl() == null)) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED,"用户信息为空或信息不全");
        }
        try {
            if (user.getId() == null) {
                User sameNameUser = userService.getUserByName(user.getUserName());
                if (sameNameUser != null && sameNameUser.getUserName().equals(user.getUserName())) {
                    return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "用户名已存在");
                }
                user.setUserRoleId(Constants.CUSTOMER_ROLE_ID);
                user.setLevel(null);
                user.setPassword(MD5Util.getMD5String(user.getPassword()));
                userService.saveAndApplyAgent(user); //添加用户 绑定角色
            } else {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您无权修改");
            }
        } catch (Exception e) {
            logger.error("{}", e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "添加失败");
        }
        return AjaxResult.success(objectToDto(user));
    }


    /**
     * 前台登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ApiOperation(value = "前台登录", httpMethod = "POST", notes = "前台登录")
    public AjaxResult customerLogin(@ApiParam(value = "用户对象") @RequestBody User user) {
        user.setPassword(MD5Util.getMD5String(user.getPassword()));
        User userDb = userService.checkCustomerLogin(user);
        if (userDb != null) {
            return doLogin(user.getUserName(), user.getPassword());
        } else {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED,"用户名或密码错误");
        }
    }

    /**
     * 前台登录
     *
     * @param userName
     * @param password
     * @return
     */
    private AjaxResult doLogin(String userName, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            User user = userService.getUserByName(userName);
            return AjaxResult.success(objectToDto(user), "成功登录");
        } catch (AuthenticationException e) {
            logger.error("{}", e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您的账号或密码输入错误");
        } finally {
        }
    }

    @RequestMapping(value = {"user/{userId}"}, method = RequestMethod.GET)
    @RequiresPermissions(value = "user:detail")
    @ApiOperation(value = "前台查看用户详情", httpMethod = "GET", notes = "前台查看用户详情")
    public AjaxResult getUserDetail(@ApiParam(value = "用户ID") @PathVariable Long userId) {
        if (userId != null) {
            Subject subject = SecurityUtils.getSubject();
            String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
            User userDb = userService.getUserById(userId);
            if (userDb != null) {
                if (!userDb.getUserName().equals(userName)) {
                    return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您无权查看他人的详情");
                }
                return AjaxResult.success(objectToDto(userDb));
            } else {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在的用户");
            }
        } else {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
    }

    @RequestMapping(value = {"user/agentApply/{userId}"}, method = RequestMethod.GET)
    @RequiresPermissions(value = "user:detail")
    @ApiOperation(value = "前台查看代理商申请进展", httpMethod = "GET", notes = "前台查看代理商申请进展")
    public AjaxResult getAgentApplyByUserId(@ApiParam(value = "用户ID") @PathVariable Long userId) {
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        AgentApplyDto agentApplyDto = agentApplyService.getDtoByUserId(userId);
        if (agentApplyDto == null) {
//            AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "不存在的代理商申请");
            return AjaxResult.emptyArray();
        }
        User userDb = userService.getUserById(userId);
        if (userDb != null) {
            if (!userDb.getUserName().equals(userName)) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您无权查看他人的代理商申请进展");
            }
            List<Map> result = Lists.newArrayList();
            result = assembleAgentApplyResult(result, agentApplyDto);
            return AjaxResult.success(result);
        } else {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该用户");
        }
    }

    @RequestMapping(value = {"user/isvApply/{userId}"}, method = RequestMethod.GET)
    @RequiresPermissions(value = "user:detail")
    @ApiOperation(value = "前台查看ISV申请进展", httpMethod = "GET", notes = "前台查看ISV申请进展")
    public AjaxResult getIsvApplyByUserId(@ApiParam(value = "用户ID") @PathVariable Long userId) {
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal() != null ? subject.getPrincipal().toString() : null;
        IsvApplyDto isvApplyDto = isvApplyService.getDtoByUserId(userId);
        if (isvApplyDto == null) {
//            AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "不存在的ISV申请");
            return AjaxResult.emptyArray();
        }
        User userDb = userService.getUserById(userId);
        if (userDb != null) {
            if (!userDb.getUserName().equals(userName)) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "您无权查看他人的ISV申请进展");
            }
            List<Map> result = Lists.newArrayList();
            result = assembleIsvApplyResult(result, isvApplyDto);
            return AjaxResult.success(result);
        } else {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该用户");
        }
    }

    /**
     * 组装代理商进展的返回数据
     *
     * @param result
     * @param agentApplyDto
     * @return
     */
    private List<Map> assembleAgentApplyResult(List<Map> result, AgentApplyDto agentApplyDto) {
        //遍历3次总共分3个状态(1. 已提交 2. 待审核 3. 通过/失败)
        for (int i = 0; i < 3; i++) {
            Map map = Maps.newHashMap();
            String status = agentApplyDto.getStatus();
            String flag = i == Integer.valueOf(status) || (3 == Integer.valueOf(status) && i == 2) ? "1" : "0";
            map.put("flag", flag);
            String desc = i == Integer.valueOf(status) || (3 == Integer.valueOf(status) && i == 2) ? agentApplyDto.getDescription() : "";
            map.put("description", desc);
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

    /**
     * 组装ISV进展的返回数据
     *
     * @param result
     * @param isvApplyDto
     * @return
     */
    private List<Map> assembleIsvApplyResult(List<Map> result, IsvApplyDto isvApplyDto) {
        //遍历3次总共分3个状态(1. 已提交 2. 待审核 3. 通过/失败)
        for (int i = 0; i < 3; i++) {
            Map map = Maps.newHashMap();
            map.put("flag", (i == Integer.valueOf(isvApplyDto.getStatus()) ? "1" : "0"));
            String status = isvApplyDto.getStatus();
            String desc = i == Integer.valueOf(status) || (3 == Integer.valueOf(status) && i == 2) ? isvApplyDto.getDescription() : "";
            map.put("description", desc);
            //如果遍历到了通过/失败
            if (i == 2) {
                if (STATUS_SUCCESS == Integer.valueOf(isvApplyDto.getStatus()) || STATUS_FAILED == Integer.valueOf(isvApplyDto.getStatus())) {
                    map.put("status", STATUS_SUCCESS != Integer.valueOf(isvApplyDto.getStatus()) && STATUS_FAILED != Integer.valueOf(isvApplyDto.getStatus()) ? "" : isvApplyDto.getStatus());
                }
            }
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = {"user/loginOut"}, method = RequestMethod.POST)
    @ApiOperation(value = "前台注销", httpMethod = "POST", notes = "前台注销")
    public AjaxResult customerLogOut() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception e) {
            logger.error("{}", e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "注销失败");
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
        dto.setLevel(user.getLevel() == null ? "" : String.valueOf(user.getLevel()));
        if (user.getLevel() != null) {
            Integer levelId = user.getLevel();
            List<String> levelList = new ArrayList<String>(Arrays.asList(AgentLevelType.AGENT.getName(), AgentLevelType.GOLD.getName(), AgentLevelType.PLATINUM.getName(), AgentLevelType.DIAMOND.getName()));
            String levelName = levelList.get(levelId);
            dto.setLevelName(levelName);
        } else {
            dto.setLevelName("");
        }
        dto.setEmailAddress(user.getEmailAddress() == null ? "" : user.getEmailAddress());
        return dto;
    }

}
