package cn.muye.support;

import cn.muye.bean.AjaxResult;
import cn.muye.cache.CommonCache;
import cn.muye.cache.RedissonUtil;
import cn.muye.config.CustomProperties;
import cn.muye.user.domain.Token;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

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

	private RedissonUtil redissonUtil;

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

		//验证是否有登录
		CommonCache commonCache = redissonUtil.commonCache();
		Map<String, Token> tokenMap = commonCache.getTokenCache();
		if (!tokenMap.containsKey(requestToken)) {
			AjaxResult ajaxResult = AjaxResult.failed(-1, "用户未登录");
			this.returnJson(httpResponse, JSON.toJSONString(ajaxResult));
		}

		//验证token是否过期
		Token token = tokenMap.get(requestToken);
		Date expireTime = token.getExpireTime();
		if(expireTime.before(new Date())){
			AjaxResult ajaxResult = AjaxResult.failed(-1, "token过期");
			this.returnJson(httpResponse, JSON.toJSONString(ajaxResult));
		}

		//更新token的超时时间,添加到redis
		long newExpireTime = System.currentTimeMillis() + customProperties.getTokenExpireTime();
		token.setExpireTime(new Date(newExpireTime));
		tokenMap.put(requestToken, token);
		commonCache.setTokenCache(tokenMap);
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

	public void setRedissonUtil(RedissonUtil redissonUtil) {
		this.redissonUtil = redissonUtil;
	}
}
