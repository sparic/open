package cn.muye.appauth.api.controller;

import cn.muye.appauth.api.service.AppAuthService;
import cn.muye.appauth.domain.AppAuth;
import cn.muye.appauth.dto.AppAuthDto;
import cn.muye.appauth.dto.AuthDto4App;
import cn.muye.core.AjaxResult;
import cn.muye.core.AjaxResult4App;
import cn.muye.core.Constants;
import cn.muye.user.api.service.UserService;
import cn.muye.user.domain.User;
import cn.muye.utils.AES;
import cn.muye.utils.DateTimeUtils;
import cn.muye.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
@RestController
public class AppAuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppAuthController.class);

    @Autowired
    private AppAuthService appAuthService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "appAuth/{userId}", method = RequestMethod.GET)
    @RequiresPermissions("isvApply:detail")
    public AjaxResult myAuthDetail(@PathVariable String userId) {
        if (userId != null) {
            User userDb = userService.getUserById(Long.valueOf(userId));
            if (userDb.getLevel() == null || userDb.getLevel() == 0) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "请先申请成为ISV");
            }
            AppAuth appAuth = appAuthService.getByUserId(Long.valueOf(userId));
            return AjaxResult.success(objectToDto(appAuth), "查询成功");
        } else {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
    }

    @RequestMapping(value = "appAuth", method = RequestMethod.GET)
    public byte[] getAuthInfoByAppId(@RequestParam(value = "appId") String appId, @RequestParam(value = "snCode") String snCode) {
        LOGGER.info("appId: " + appId);
        try {
            if (StringUtil.isNullOrEmpty(appId) || StringUtil.isNullOrEmpty(snCode)) {
                return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_PARAM, "参数有误"));
            } else {
                AppAuth appAuthDb = appAuthService.getByAppId(appId);
                if (appAuthDb != null) {
                    String snCodeStr = appAuthDb.getSnCodeArr();
                    //获取已绑定的数量
                    int count = 0;
                    String[] arr = new String[]{};
                    if (!StringUtil.isNullOrEmpty(snCodeStr)) {
                        arr = snCodeStr.split(",");
                        count = arr.length;
                    }
                    //如果已经达到了上限，则返回错误
                    Boolean flag = Arrays.asList(arr).contains(snCode);
                    Boolean ifExpired = DateTimeUtils.getInternalDateByDay(appAuthDb.getEndTime(), 1).getTime() < System.currentTimeMillis();
                    if (ifExpired) {
                        return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_EXPIRED, "授权已到期"));
                    }
                    if (flag) {
                        AuthDto4App dto = new AuthDto4App();
                        dto.setAppId(appId);
                        dto.setSnCode(snCode);
                        dto.setEndTime(DateTimeUtils.getDateString(appAuthDb.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
                        return aesEncode(AjaxResult4App.success(dto, "查询成功"));
                    }
                    if (count >= appAuthDb.getAuthLimit()) {
                        return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_LIMIT, "授权机器已达上限"));
                    } else {
                        appAuthDb.setSnCodeArr(!StringUtil.isNullOrEmpty(snCodeStr) ? snCodeStr + "," + snCode : snCode);
                        appAuthService.update(appAuthDb);
                        AuthDto4App dto = new AuthDto4App();
                        dto.setAppId(appId);
                        dto.setSnCode(snCode);
                        dto.setEndTime(DateTimeUtils.getDateString(appAuthDb.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
                        return aesEncode(AjaxResult4App.success(dto, "新增成功"));
                    }
                } else {
                    return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_NOT_EXIST, "AppId不存在"));
                }
            }
        } catch (Exception e) {
            LOGGER.error("编码错误", e.getMessage());
            return null;
        }
    }

    /* 自己测试用
    @RequestMapping(value = "appAuth", method = RequestMethod.GET)
    public AjaxResult4App getAuthInfoByAppId(@RequestParam(value = "appId") String appId, @RequestParam(value = "snCode") String snCode) {
        if (appId == null || snCode == null) {
            return AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_PARAM, "参数有误");
        } else {
            AppAuth appAuthDb = appAuthService.getByAppId(appId);
            if (appAuthDb != null) {
                String snCodeStr = appAuthDb.getSnCodeArr();
                //获取已绑定的数量
                String[] arr = snCodeStr.split(",");
                int count = arr.length;
                //如果已经达到了上限，则返回错误
                Boolean flag = Arrays.asList(arr).contains(snCode);
                Boolean ifExpired = DateTimeUtils.getInternalDateByDay(appAuthDb.getEndTime(), 1).getTime() < System.currentTimeMillis();
                if (ifExpired) {
                    return AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_EXPIRED, "授权已到期");
                } else {
                    if (count == Constants.APP_AUTH_SN_LIMIT && !flag) {
                        return AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_LIMIT, "授权机器已达上限");
                    } else if (count == Constants.APP_AUTH_SN_LIMIT && flag) {
                        AuthDto4App dto = new AuthDto4App();
                        dto.setAppId(appId);
                        dto.setSnCode(snCode);
                        dto.setEndTime(DateTimeUtils.getDateString(appAuthDb.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
                        return AjaxResult4App.success(dto, "查询成功");
                    } else if (count < Constants.APP_AUTH_SN_LIMIT && flag) { //否则新增一条sn
                        appAuthDb.setSnCodeArr(snCodeStr + "," + snCode);
                        appAuthService.update(appAuthDb);
                        AuthDto4App dto = new AuthDto4App();
                        dto.setAppId(appId);
                        dto.setSnCode(snCode);
                        dto.setEndTime(DateTimeUtils.getDateString(appAuthDb.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
                        return AjaxResult4App.success(dto, "新增成功");
                    }
                }
            } else {
                return AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_NOT_EXIST, "AppId不存在");
            }
        }
        return null;
    }*/

    private static byte[] aesEncode(AjaxResult4App ajaxResult4App) throws UnsupportedEncodingException {
        String result = JSON.toJSONString(ajaxResult4App);
        byte[] encryptResult = AES.encrypt(result.getBytes("UTF-8"), Constants.AES_KEY.getBytes("UTF-8"));//加密
        return encryptResult;
    }

    private AppAuthDto objectToDto(AppAuth appAuth) {
        AppAuthDto appAuthDto = new AppAuthDto();
        appAuthDto.setAppId(appAuth.getAppId());
        appAuthDto.setEndTime(DateTimeUtils.getDateString(appAuth.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        if (appAuth.getSnCodeArr() != null) {
            String arr[] = appAuth.getSnCodeArr().split(",");
            appAuthDto.setSnCount(arr.length);
        } else {
            appAuthDto.setSnCount(0);
        }
        return appAuthDto;
    }

}
