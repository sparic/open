package cn.muye.appauth.adminApi.controller;

import cn.muye.appauth.adminApi.service.AdminAppAuthService;
import cn.muye.appauth.domain.AppAuth;
import cn.muye.appauth.dto.AppAuthDto;
import cn.muye.core.AjaxResult;
import cn.muye.utils.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
@RestController
public class AdminAppAuthController {

    @Autowired
    private AdminAppAuthService adminAppAuthService;

    @RequestMapping(value = {"admin/appAuth"}, method = RequestMethod.GET)
    @RequiresPermissions("appAuth:query")
    @ApiOperation(value = "app授权列表", httpMethod = "GET", notes = "app授权列表")
    public AjaxResult listAppAuth(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize, true, null, true);
        List<AppAuth> list = adminAppAuthService.list();
        List<AppAuthDto> dtoList = Lists.newArrayList();
        if (list != null) {
            for (AppAuth appAuth : list) {
                dtoList.add(objectToDto(appAuth));
            }
        }
        PageInfo<AppAuthDto> agentApplyDtoPageInfo = new PageInfo(list);
        agentApplyDtoPageInfo.setList(dtoList);
        return AjaxResult.success(agentApplyDtoPageInfo);
    }

    private AppAuthDto objectToDto(AppAuth appAuth) {
        AppAuthDto appAuthDto = new AppAuthDto();
        appAuthDto.setAppId(appAuth.getAppId());
        appAuthDto.setUserId(appAuth.getUserId());
        appAuthDto.setEndTime(DateTimeUtils.getDateString(appAuth.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        appAuthDto.setStartTime(DateTimeUtils.getDateString(appAuth.getStartTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        String arr[] = appAuth.getSnCodeArr().split(",");
        appAuthDto.setSnCount(arr.length);
        return appAuthDto;
    }
}
