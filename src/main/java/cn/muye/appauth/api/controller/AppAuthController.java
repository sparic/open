package cn.muye.appauth.api.controller;

import cn.muye.appauth.api.service.AppAuthService;
import cn.muye.appauth.domain.AppAuth;
import cn.muye.appauth.dto.AppAuthDto;
import cn.muye.appauth.dto.AuthDto4App;
import cn.muye.core.AjaxResult;
import cn.muye.core.AjaxResult4App;
import cn.muye.core.Constants;
import cn.muye.utils.AES;
import cn.muye.utils.DateTimeUtils;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
@RestController
public class AppAuthController {

    @Autowired
    private AppAuthService appAuthService;

    @RequestMapping(value = "appAuth/{userId}", method = RequestMethod.GET)
    @RequiresPermissions("isvApply:detail")
    public AjaxResult myAuthDetail(@PathVariable String userId) {
        if (userId != null) {
            AppAuth appAuth = appAuthService.getByUserId(Long.valueOf(userId));
            return AjaxResult.success(objectToDto(appAuth), "查询成功");
        } else {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
    }

    @RequestMapping(value = "appAuth", method = RequestMethod.GET)
    public byte[] getAuthInfoByAppId(@RequestParam(value = "appId") String appId, @RequestParam(value = "snCode") String snCode) {
        if (appId == null || snCode == null) {
            return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_PARAM, "参数有误"));
        } else {
            AppAuth appAuthDb = appAuthService.getByAppId(appId);
            if (appAuthDb != null) {
                String snCodeStr = appAuthDb.getSnCodeArr();
                //获取已绑定的数量
                String[] arr = snCodeStr.split(",");
                int count = arr.length;
                //如果已经达到了上限，则返回错误
                Boolean flag = Arrays.asList(arr).contains(snCode);
                Boolean ifExpired = appAuthDb.getEndTime().getTime() < System.currentTimeMillis();
                if (ifExpired) {
                    return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_EXPIRED, "授权已到期"));
                } else {
                    if (count == Constants.APP_AUTH_SN_LIMIT && !flag) {
                        return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_LIMIT, "授权机器已达上限"));
                    } else if (count == Constants.APP_AUTH_SN_LIMIT && flag) {
                        AuthDto4App dto = new AuthDto4App();
                        dto.setAppId(appId);
                        dto.setEndTime(DateTimeUtils.getDateString(appAuthDb.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
                        return aesEncode(AjaxResult4App.success(dto, "查询成功"));
                    } else if (count < Constants.APP_AUTH_SN_LIMIT && flag) { //否则新增一条sn
                        appAuthDb.setSnCodeArr(snCodeStr + "," + snCode);
                        appAuthService.update(appAuthDb);
                        AuthDto4App dto = new AuthDto4App();
                        dto.setAppId(appId);
                        dto.setEndTime(DateTimeUtils.getDateString(appAuthDb.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
                        return aesEncode(AjaxResult4App.success(dto, "新增成功"));
                    }
                }
            } else {
                return aesEncode(AjaxResult4App.failed(AjaxResult4App.CODE_ERROR_NOT_EXIST, "AppId不存在"));
            }
        }
        return null;
    }

    private static byte[] aesEncode(AjaxResult4App ajaxResult4App) {
        String result = JSON.toJSONString(ajaxResult4App);
        byte[] encryptResult = AES.encrypt(result.getBytes(), Constants.AES_KEY.getBytes());//加密
        return encryptResult;
    }

    private AppAuthDto objectToDto(AppAuth appAuth) {
        AppAuthDto appAuthDto = new AppAuthDto();
        appAuthDto.setAppId(appAuth.getAppId());
        appAuthDto.setEndTime(DateTimeUtils.getDateString(appAuth.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        String arr[] = appAuth.getSnCodeArr().split(",");
        appAuthDto.setSnCount(arr.length);
        return appAuthDto;
    }

}
