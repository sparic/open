package cn.muye.appauth.adminApi.controller;

import cn.muye.appauth.adminApi.service.AdminAppAuthService;
import cn.muye.appauth.domain.AppAuth;
import cn.muye.appauth.dto.AppAuthDto;
import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.user.api.service.UserService;
import cn.muye.user.domain.User;
import cn.muye.utils.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"admin/appAuth"}, method = RequestMethod.GET)
    @RequiresPermissions("appAuth:query")
    @ApiOperation(value = "app授权列表", httpMethod = "GET", notes = "app授权列表")
    public AjaxResult listAppAuth(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize, true, null, true);
        List<AppAuthDto> list = adminAppAuthService.list();
        if (list != null) {
            for (AppAuthDto dto : list) {
                if (dto.getSnCodeArr() != null) {
                    String arr[] = dto.getSnCodeArr().split(",");
                    dto.setSnCount(arr.length);
                } else {
                    dto.setSnCount(0);
                }
            }
        }
        PageInfo<AppAuthDto> agentApplyDtoPageInfo = new PageInfo(list);
        agentApplyDtoPageInfo.setList(list);
        return AjaxResult.success(agentApplyDtoPageInfo);
    }

    @RequestMapping(value = {"admin/appAuth"}, method = RequestMethod.POST)
    @RequiresPermissions("appAuth:update")
    @ApiOperation(value = "app授权修改", httpMethod = "POST", notes = "app授权修改")
    public AjaxResult update(@RequestBody AppAuth appAuth) {
        if (appAuth != null && (appAuth.getId() == null || appAuth.getAuthLimit() == null || appAuth.getExtraPeriod() == null)) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
        AppAuth appAuthDb = adminAppAuthService.getById(appAuth);
        if (appAuthDb != null) {
            appAuthDb.setAuthLimit(appAuth.getAuthLimit());
            appAuthDb.setEndTime(DateTimeUtils.getInternalTimeByMonth(appAuthDb.getEndTime(), appAuth.getExtraPeriod()));
            adminAppAuthService.update(appAuthDb);
            return AjaxResult.success(objectToDto(appAuthDb), "修改成功");
        } else {
            return AjaxResult.failed("该app授权信息不存在");
        }
    }


    private AppAuthDto objectToDto(AppAuth appAuth) {
        AppAuthDto appAuthDto = new AppAuthDto();
        appAuthDto.setAppId(appAuth.getAppId());
        appAuthDto.setAuthLimit(appAuth.getAuthLimit());
        appAuthDto.setEndTime(DateTimeUtils.getDateString(appAuth.getEndTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        appAuthDto.setId(appAuth.getId());
        appAuthDto.setSnCodeArr(appAuth.getSnCodeArr());
        if (appAuth.getSnCodeArr() != null) {
            String arr[] = appAuth.getSnCodeArr().split(",");
            appAuthDto.setSnCount(arr.length);
        } else {
            appAuthDto.setSnCount(0);
        }
        appAuthDto.setStartTime(DateTimeUtils.getDateString(appAuth.getStartTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        User userDb = userService.getUserById(appAuth.getUserId());
        appAuthDto.setUserName(userDb.getUserName());
        return appAuthDto;
    }
}
