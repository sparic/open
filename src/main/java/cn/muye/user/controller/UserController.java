package cn.muye.user.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.cache.CommonCache;
import cn.muye.cache.RedissonUtil;
import cn.muye.config.CustomProperties;
import cn.muye.user.domain.LoginInfo;
import cn.muye.user.domain.Token;
import cn.muye.user.domain.User;
import cn.muye.user.service.UserService;
import cn.muye.utils.TokenUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

	@Autowired
	private CustomProperties customProperties;
	@Autowired
	private UserService userService;

	@Autowired
	private RedissonUtil redissonUtil;

	/**
	 * 登录接口
	 *
	 * @param loginInfoStr
	 * @return
	 */
	@RequestMapping(value = {"admin/user/login", "user/login"}, method = {RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value = "登录", httpMethod = "POST", notes = "登录（type:页面类型（1:后台页面 3：前台页面））")
	public AjaxResult login(@RequestBody String loginInfoStr) {
		LoginInfo loginInfo = JSON.parseObject(loginInfoStr, LoginInfo.class);
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
	}

	/**
	 * 较验用户名
	 *
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = {"admin/validate"}, method = RequestMethod.GET)
	@ApiOperation(value = "校验用户名", httpMethod = "GET", notes = "校验用户名")
	public AjaxResult validateUserName(@RequestParam(value = "userName") String userName) {
		User user = userService.getUserByName(userName);
		if (null != user) {
			return AjaxResult.failed(-1, "用户名已存在!");
		}
		return AjaxResult.success();
	}

	/**
	 * 添加用户
	 *
	 * @param userStr
	 * @return
	 */
	@RequestMapping(value = "admin/user", method = RequestMethod.POST)
	@ApiOperation(value = "添加/更新用户", httpMethod = "POST", notes = "添加/更新用户")
	public AjaxResult addUser(@ApiParam(value = "用户对象（userType：1：超管 2：管理员 3：普通用户）") @RequestBody String userStr) {
		User user = JSON.parseObject(userStr, User.class);
		if(null == user){
			return AjaxResult.failed("用户信息为空");
		}
		//TODO 生成秘钥

		if(user.getId() != null && user.getId() >= 1){
			userService.update(user); //更新用户
		}else {
			userService.save(user); //添加用户
		}
		return AjaxResult.success();
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
		PageInfo<User> userPageInfo = new PageInfo(userList);
		userPageInfo.setList(userList);
		return AjaxResult.success(userPageInfo);
	}


	/**
	 * 较验用户名
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"admin/user/loginOut", "user/loginOut"}, method = RequestMethod.POST)
	@ApiOperation(value = "注销", httpMethod = "GET", notes = "注销")
	public AjaxResult loginOut(HttpServletRequest request) {
		//清除redis中的token 数据
		String tokenCode = request.getParameter("token");
		//将token存入redis
		CommonCache commonCache = redissonUtil.commonCache();
		Map<String, Token> tokenCache = commonCache.getTokenCache();
		tokenCache.remove(tokenCode);
		commonCache.setTokenCache(tokenCache);
		return AjaxResult.success();
	}
}
