package cn.muye.user.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.Constants;
//import cn.muye.cache.CommonCache;
//import cn.muye.cache.RedissonUtil;
import cn.muye.config.CustomProperties;
import cn.muye.user.domain.LoginInfo;
import cn.muye.user.domain.Token;
import cn.muye.user.domain.User;
import cn.muye.user.dto.UserDto;
import cn.muye.user.service.UserService;
//import cn.muye.utils.TokenUtils;
import cn.muye.util.DateTimeUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private CustomProperties customProperties;

    @Autowired
    private UserService userService;

//    @Autowired
//    private RedissonUtil redissonUtil;

    /**
     * 登录接口
     *
     * @param loginInfo
     * @return
     *//*
    @RequestMapping(value = {"admin/user/login", "user/login"}, method = {RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value = "登录", httpMethod = "POST", notes = "登录（type:页面类型（1:后台页面 3：前台页面））")
	public AjaxResult login(@RequestBody LoginInfo loginInfo) {
		String userName = loginInfo.getUserName();
		int type = loginInfo.getType();
		String md5  = loginInfo.getMd5();
		User user = userService.getUserByName(userName);
		if (null == user) {
			return AjaxResult.failed("用户名不存在!");
		}
		//校验用户权限
		if(user.getUserType() > type){
			return AjaxResult.failed("非法操作");
		}
		//用户名和密码检验通过，签发token
		String password = user.getPassword();
		String localMD5 = TokenUtils.getUserMD5(userName, password);
		if (localMD5.equals(md5)) {
			String tokenCode = TokenUtils.createToken(userName);

			long expireTime = System.currentTimeMillis() + customProperties.getTokenExpireTime();
			Token token = new Token(tokenCode, new Date(expireTime));
			token.setUser(user);

			//将token存入redis
			CommonCache commonCache = redissonUtil.commonCache();
			Map<String, Token> tokenCache = commonCache.getTokenCache();
			if (null == tokenCache) {
				tokenCache = Maps.newConcurrentMap();
			}
			tokenCache.put(tokenCode, new Token(tokenCode, new Date(expireTime)));
			commonCache.setTokenCache(tokenCache);

			//更新最后访问时间
			user.setLastVisit(new Date());
			userService.update(user);

			//将token返回前端
			JSONObject jo = new JSONObject();
			jo.put("token", tokenCode);
			jo.put("userName", userName);
			jo.put("userType", user.getUserType());
			return AjaxResult.success(jo);
		}else {
			return AjaxResult.failed("用户名或密码错误");
		}
	}*/

    /**
     * 较验用户名
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = {"admin/user/validate"}, method = RequestMethod.GET)
    @ApiOperation(value = "校验用户名", httpMethod = "GET", notes = "校验用户名")
    public AjaxResult validateUserName(@ApiParam(value = "用户名") @RequestParam(value = "userName") String userName) {
        User user = userService.getUserByName(userName);
        if (null != user) {
            return AjaxResult.failed(-1, "用户名已存在!");
        }
        return AjaxResult.success();
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "admin/user", method = RequestMethod.POST)
//    @RequiresPermissions("user:upsert")
    @ApiOperation(value = "添加/更新用户", httpMethod = "POST", notes = "添加/更新用户")
    public AjaxResult addUser(@ApiParam(value = "用户对象（userType：1：超管 2：管理员 3：普通用户）") @RequestBody User user) {
        if (null == user) {
            return AjaxResult.failed("用户信息为空");
        }
        //TODO 生成秘钥
        if (user.getId() != null && user.getId() >= 1) {
            User userDb = userService.getUserById(user.getId());
            if (userDb != null) {
                userDb.setPhone(user.getPhone());
                userDb.setSex(user.getSex());
                userDb.setCompany(user.getCompany());
                userDb.setEmailAddress(user.getEmailAddress());
                userDb.setPassword(user.getPassword());
                userDb.setUserName(user.getUserName());
                userDb.setDeactivated(user.isDeactivated());
                userService.update(userDb); //更新用户
                return AjaxResult.success(userDb);
            } else {
                return AjaxResult.failed("不存在该用户");
            }
        } else {
            userService.saveAndSendMail(user); //添加用户
            return AjaxResult.success(user);
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
    @ApiOperation(value = "列出全部用户", httpMethod = "GET", notes = "列出全部用户")
    public AjaxResult getUserList(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> userList = userService.getUserList(page);
        List<UserDto> userDtoList = Lists.newArrayList();
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                if (user.getUserType() != 1) {
                    userDtoList.add(objectToDto(user));
                }
            }
        }
        PageInfo<UserDto> userPageInfo = new PageInfo(userList);
        userPageInfo.setList(userDtoList);
        return AjaxResult.success(userPageInfo);
    }

    private UserDto objectToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
//        dto.setAppKey(user.getAppKey());
//        dto.setAppSecret(user.getAppSecret());
        dto.setCompany(user.getCompany());
        dto.setDeactivatedStatus(user.isDeactivated());
        dto.setEmailAddress(user.getEmailAddress());
        dto.setLastVisit(DateTimeUtils.getDateString(user.getLastVisit(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
//        dto.setPasswordChangeRequired(user.isPasswordChangeRequired());
//        dto.setReceiveEmail(user.isReceiveEmail());
//        dto.setRegistered(user.isRegistered());
        dto.setSex(user.getSex());
        return dto;
    }

    /**
     * 删除用户
     * <p>
     * //     * @param user
     *
     * @return
     */
    @RequestMapping(value = "admin/user/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", httpMethod = "DELETE", notes = "删除用户")
    public AjaxResult deleteUser(@ApiParam(value = "用户ID") @PathVariable Long id) {
        if (id != null) {
            User userDb = userService.getUserById(id);
            if (userDb != null) {
                UserDto dto = objectToDto(userDb);
                if (dto.getRoleName().contains(Constants.SUPER_ADMIN)) {
                    return AjaxResult.failed("不能删除超级管理员");
                }
                userDb.setDeactivated(true);
                userService.deleteById(userDb.getId());
                return AjaxResult.success();
            }
        } else {
            return AjaxResult.failed("不存在该用户");
        }
        return null;
    }

    @RequestMapping(value = "admin/user/role", method = RequestMethod.POST)
    @ApiOperation(value = "绑定用户角色", httpMethod = "POST", notes = "绑定用户角色")
    public AjaxResult bindUserRole(@ApiParam(value = "角色ID集合,数组类型[]，逗号分隔") @RequestParam String roleIdListStr, @ApiParam(value = "用户ID") @RequestParam Long userId) {
        try {
            List<Long> roleIdList = JSON.parseArray(roleIdListStr, Long.class);
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            return AjaxResult.failed("绑定失败");
        } finally {
        }
        return AjaxResult.success();
    }

    /**
     * 后台登录
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "admin/login", method = RequestMethod.POST)
    public AjaxResult adminLogin(@ApiParam(value = "用户名") @RequestParam(value = "userName", required = true) String userName,
                                 @ApiParam(value = "密码") @RequestParam(value = "password", required = true) String password/*,
                            @ApiParam(value = "记住我") @RequestParam(value = "rememberMe", required = true, defaultValue = "false") boolean rememberMe*/) {
        return AjaxResult.success();
    }

    @RequestMapping(value = {"admin/user/loginOut"}, method = RequestMethod.POST)
    @ApiOperation(value = "注销", httpMethod = "POST", notes = "注销")
    public AjaxResult adminLogOut() {
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            subject.logout();
//        } catch (Exception e) {
//            logger.error("{}", e);
//            return AjaxResult.failed("注销失败");
//        }
        return AjaxResult.success();
    }
}
