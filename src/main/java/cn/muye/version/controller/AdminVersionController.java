package cn.muye.version.controller;

import cn.muye.core.AjaxResult;
import cn.muye.utils.DateTimeUtils;
import cn.muye.version.domain.Version;
import cn.muye.version.dto.VersionDto;
import cn.muye.version.service.AdminVersionService;
import cn.muye.version.service.VersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Created by Ray.Fu on 2017/5/2.
 */
@RestController
public class AdminVersionController {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminVersionController.class);

    @Autowired
    private AdminVersionService adminVersionService;

    /**
     * 后台查询版本列表接口
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = {"admin/version"}, method = RequestMethod.GET)
    @RequiresPermissions("version:query")
    @ApiOperation(value = "后台查询版本列表", httpMethod = "GET", notes = "后台查询版本列表")
    public AjaxResult listVersionsAdmin(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Version> list = adminVersionService.listVersions();
        List<VersionDto> listDto = Lists.newArrayList();
        if (list != null && list.size() > 0) {
            for (Version version : list) {
                listDto.add(objectToDtoAdmin(version));
            }
        }
        PageInfo<VersionDto> versionPageInfo = new PageInfo(list);
        versionPageInfo.setList(listDto);
        return AjaxResult.success(versionPageInfo);
    }

    private VersionDto objectToDtoAdmin(Version version) {
        VersionDto dto = new VersionDto();
        dto.setUrl(version.getUrl());
        dto.setCreateTime(DateTimeUtils.getDateString(version.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setUpdateTime(DateTimeUtils.getDateString(version.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setDescription(version.getDescription());
        dto.setExtendedVersionCode(version.getExtendedVersionCode());
        dto.setId(version.getId());
        dto.setVersionCode(version.getVersionCode());
        return dto;
    }

    /**
     * 新增版本接口
     *
     * @param version
     * @return
     */
    @RequestMapping(value = {"admin/version"}, method = RequestMethod.POST)
    @RequiresPermissions("version:upsert")
    @ApiOperation(value = "后台新增或修改版本", httpMethod = "POST", notes = "后台新增或修改版本")
    public AjaxResult adminPostVersion(@ApiParam(value = "版本对象") @RequestBody Version version) {
        if (version != null && (version.getVersionCode() == null || version.getUrl() == null)) {
            return AjaxResult.failed("信息不全，请补全后提交");
        }
        return addOrUpdate(version);
    }

    private AjaxResult addOrUpdate(Version version) {
        if (StringUtils.isNullOrEmpty(version.getUrl())) {
            return AjaxResult.failed("请上传sdk文件");
        }
        //修改
        if (version.getId() != null) {
            Version versionDb = adminVersionService.getById(version.getId());
            if (versionDb != null) {
                versionDb.setVersionCode(version.getVersionCode());
                versionDb.setDescription(version.getDescription());
                versionDb.setUpdateTime(new Date());
                versionDb.setUrl(version.getUrl());
                adminVersionService.updateVersion(versionDb);
                return AjaxResult.success(objectToDtoAdmin(versionDb));
            } else {
                return AjaxResult.failed();
            }
        } else {
            //拿到extendedVersionCode 去数据库查这个对应值
            //ps:extendedVersionCode这个字段其实是表的ID
            String extendId = version.getExtendedVersionCode();
            if (!StringUtils.isNullOrEmpty(extendId)) {
                version = adminVersionService.copyVersion(Long.valueOf(extendId), version);
                return AjaxResult.success(version);
            }
            version.setCreateTime(new Date());
            version.setExtendedVersionCode(null);
            adminVersionService.saveVersion(version);
            return AjaxResult.success(objectToDtoAdmin(version));
        }

    }

    /**
     * 后台获取单个版本
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"admin/version/{id}"}, method = RequestMethod.GET)
    @RequiresPermissions("version:detail")
    @ResponseBody
    @ApiOperation(value = "后台获取单个版本", httpMethod = "GET", notes = "后台获取单个版本")
    public AjaxResult getVersionAdmin(@ApiParam(value = "版本ID") @PathVariable Long id) {
        Version version = adminVersionService.getById(id);
        VersionDto versionDto = objectToDtoAdmin(version);
        return AjaxResult.success(versionDto);
    }

    /**
     * 修改版本
     * todo spring不能接受put方法的传参的问题
     * @param id
     * @param versionStr
     * @return
     */
//    @RequestMapping(value = {"admin/version/{id}"}, method = RequestMethod.PUT)
//    @ApiOperation(value = "修改版本", httpMethod = "PUT", notes = "修改版本")
//    public AjaxResult putVersion(/*@ApiParam(value = "版本ID")*/ @PathVariable Long id, /*@ApiParam(value = "版本对象")*/ @RequestBody String versionStr) {
////        Version version = JSON.parseObject(versionStr, Version.class);
//        Version version = null;
//        if (StringUtils.isNullOrEmpty(version.getUrl())) {
//            return AjaxResult.failed("请上传sdk文件");
//        }
//        Version versionDb = versionService.getById(id);
//        if (versionDb != null) {
////            String extendId = version.getExtendedVersionCode();
////            if (!StringUtils.isNullOrEmpty(extendId)) {
////                Version versionNew = versionService.copyVersion(Long.valueOf(extendId), version.getVersionCode(), version.getDescription());
////                return AjaxResult.success(versionNew);
////            }
//            versionDb.setVersionCode(version.getVersionCode());
//            versionDb.setDescription(version.getDescription());
//            versionDb.setUpdateTime(new Date());
//            versionService.updateVersion(versionDb);
//            return AjaxResult.success(objectToDto(versionDb));
//        } else {
//            return AjaxResult.failed();
//        }
//    }

    /**
     * 删除版本
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"admin/version/{id}"}, method = RequestMethod.DELETE)
    @RequiresPermissions("version:delete")
    @ApiOperation(value = "后台删除版本", httpMethod = "DELETE", notes = "后台删除版本")
    public AjaxResult deleteVersionAdmin(@ApiParam(value = "版本ID") @PathVariable Long id) {
        try {
            adminVersionService.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("不能刪除有绑定菜单或SDK的版本", e);
            return AjaxResult.failed("不能刪除有绑定菜单或SDK的版本");
        } finally {
        }
        return AjaxResult.success("删除成功");
    }

}
