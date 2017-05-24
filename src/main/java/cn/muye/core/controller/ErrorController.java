package cn.muye.core.controller;

import cn.muye.core.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ErrorController extends BasicErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @Autowired
    public ErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的Json响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<String, Object>();
        LOGGER.error(body.toString());
        String className = body.get("exception").toString();
        if (className.equals("org.apache.shiro.authz.UnauthenticatedException") || className.equals("org.apache.shiro.authc.AuthenticationException")) {
            map.put("msg", "用户未登录");
            map.put("code", AjaxResult.CODE_LOG_FAILED);
        } else if (className.equals("org.apache.shiro.authz.UnauthorizedException") || className.equals("org.apache.shiro.authz.AuthorizationException")){
            map.put("msg", "用户无权限");
            map.put("code", AjaxResult.CODE_LOG_FAILED);
        } else if (className.equals("java.lang.IllegalArgumentException") || className.equals("org.springframework.web.bind.MissingServletRequestParameterException")) {
            map.put("msg", "参数有误");
            map.put("code", AjaxResult.CODE_PARAM_MISTAKE_FAILED);
        } else if (status.equals(404)){
            map.put("msg", "不存在的地址");
        } else {
            map.put("msg", "系统内部错误");
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        //指定自定义的视图
        return new ModelAndView("system/404", model);
    }
}