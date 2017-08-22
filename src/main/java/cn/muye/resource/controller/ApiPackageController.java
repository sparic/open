package cn.muye.resource.controller;

import cn.muye.config.CustomProperties;
import cn.muye.core.AjaxResult;
import cn.muye.resource.domain.ApiPackage;
import cn.muye.resource.service.ApiPackageService;
import cn.muye.utils.FileUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by admin on 2017/8/21.
 */
@RestController
public class ApiPackageController {

    @Autowired
    private ApiPackageService apiPackageService;

    /**
     * 保存api信息
     * @param request
     * @return
     */
    @RequestMapping(value = {"admin/apiPackage"}, method = RequestMethod.POST)
    @RequiresPermissions("apiPackage:save")
    @ApiOperation(value = "保存人机交互平台和业务应用平台文件信息", httpMethod = "POST", notes = "保存人机交互平台和业务应用平台文件信息")
    public AjaxResult addApiPackage(@RequestBody ApiPackage apiPackage, HttpServletRequest request) {
        apiPackage.setCreateTime(new Date());
        apiPackageService.saveApiPackage(apiPackage);
        return AjaxResult.success(apiPackage, "保存成功");
    }

}
