package cn.muye.support;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.Constants;
import cn.muye.cache.RedisUtil;
import cn.muye.config.CustomProperties;
import cn.muye.login.domain.Token;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import cn.muye.cache.entity.CommonCache;
//import cn.muye.cache.util.RedissonUtil;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/4
 * Time: 15:14
 * Describe:
 * Version:1.0
 */
public class HTTPJwtAuthorizeInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(HTTPJwtAuthorizeInterceptor.class);
//	@Autowired
//	private RedissonUtil redissonUtil;

	private CustomProperties customProperties;

	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {
		//验证token是否有效
		String requestToken = httpRequest.getParameter("token");
		//参数验证
		if (null == requestToken) {
			AjaxResult ajaxResult = AjaxResult.failed(-1, "token为空");
			this.returnJson(httpResponse, JSON.toJSONString(ajaxResult));
			return false;
		}
		Jedis jedis = RedisUtil.getJedis();
		List<String> tokenList = jedis.hmget(Constants.REDIS_TOKEN, requestToken);
		if (null == tokenList || tokenList.size() <= 0) {
			AjaxResult ajaxResult = AjaxResult.failed(-1, "用户未登录");
			this.returnJson(httpResponse, JSON.toJSONString(ajaxResult));
			return false;
		}
		String tokenStr = tokenList.get(0);
		Token token = JSON.parseObject(tokenStr, Token.class);
		if(null == token){
			AjaxResult ajaxResult = AjaxResult.failed(-1, "token无效");
			this.returnJson(httpResponse, JSON.toJSONString(ajaxResult));
			return false;
		}
		if (token.getExpireTime().before(new Date())) {
			AjaxResult ajaxResult = AjaxResult.failed(-1, "token过期");
			this.returnJson(httpResponse, JSON.toJSONString(ajaxResult));
			return false;
		}
		//更新token的超时时间,添加到redis
		long expireTime = System.currentTimeMillis() + customProperties.getTokenExpireTime();
		token.setExpireTime(new Date(expireTime));
		Map<String, String> tokenMap = Maps.newConcurrentMap();
		tokenMap.put(requestToken, JSON.toJSONString(token));
		jedis.hmset(Constants.REDIS_TOKEN, tokenMap);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
	}

	private void returnJson(HttpServletResponse httpResponse, String json) throws Exception {
		PrintWriter writer = null;
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json; charset=utf-8");
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		try {
			writer = httpResponse.getWriter();
			writer.print(json);

		} catch (IOException e) {
			LOG.error("response error", e);
		} finally {
			if (writer != null)
				writer.close();
		}
	}


	public CustomProperties getCustomProperties() {
		return customProperties;
	}

	public void setCustomProperties(CustomProperties customProperties) {
		this.customProperties = customProperties;
	}
}
