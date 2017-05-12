package cn.muye.user.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.Constants;
import cn.muye.cache.RedisUtil;
//import cn.muye.cache.entity.CommonCache;
//import cn.muye.cache.util.RedissonUtil;
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
import redis.clients.jedis.Jedis;

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

//	@Autowired
//	private RedissonUtil redissonUtil;

	private RedisUtil redisUtil;

	/**
	 * 登录接口
	 *
	 * @param loginInfoStr
	 * @return
	 */
	@RequestMapping(value = {"admin/user/login", "user/login"}, method = {RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value = "登录", httpMethod = "POST", notes = "登录（type:页面类型（1:后台页面 3：前台页面））")
	public AjaxResult login(@RequestBody String loginInfoStr, HttpServletRequest httpRequest) {
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

			Jedis jedis = RedisUtil.getJedis();
			Map<String, String> tokenMap = Maps.newConcurrentMap();
			long expireTime = System.currentTimeMillis() + customProperties.getTokenExpireTime();
			Token token = new Token(tokenCode, new Date(expireTime));
			token.setUser(user);
			//删除原redis中的登录token信息
			jedis.hdel(Constants.REDIS_TOKEN, tokenCode);
			//存入新的信息
			tokenMap.put(tokenCode, JSON.toJSONString(token));
			jedis.hmset(Constants.REDIS_TOKEN, tokenMap);

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
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "admin/user", method = RequestMethod.POST)
	@ApiOperation(value = "添加/更新用户", httpMethod = "POST", notes = "添加/更新用户")
	public AjaxResult addUser(@ApiParam(value = "用户对象（userType：1：超管 2：管理员 3：普通用户）") @RequestBody User user) {
		if(null == user){
			return AjaxResult.failed("用户信息为空");
		}
		//TODO 生成秘钥

		if(user.getId() >= 1){
			userService.update(user); //更新用户
		}else {
			userService.save(user); //添加用户
		}
		return AjaxResult.success(user);
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
		Jedis jedis = RedisUtil.getJedis();
		jedis.hdel(Constants.REDIS_TOKEN,tokenCode);
		return AjaxResult.success();
	}
}
