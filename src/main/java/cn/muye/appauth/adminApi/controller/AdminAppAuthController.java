package cn.muye.appauth.adminApi.controller;

import cn.muye.appauth.adminApi.service.AdminAppAuthService;
import cn.muye.appauth.dto.AppAuthDto;
import cn.muye.core.AjaxResult;
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

}
