package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.config.CustomProperties;
import cn.muye.dto.VersionDto;
import cn.muye.model.Version;
import cn.muye.service.VersionService;
import cn.muye.util.DateTimeUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
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
public class VersionController {

    private static Logger LOGGER = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    private VersionService versionService;

    @Autowired
    private CustomProperties customProperties;

    /**
     * 查询版本列表接口 admin
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = {"admin/version","/version"}, method = RequestMethod.GET)
    @ApiOperation(value = "查询版本列表", httpMethod = "GET", notes = "查询版本列表")
    public AjaxResult listVersions(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Version> list = versionService.listVersions();
        List<VersionDto> listDto = Lists.newArrayList();
        if (list != null && list.size() > 0) {
            for (Version version : list) {
                listDto.add(objectToDto(version));
            }
        }
        PageInfo<VersionDto> versionPageInfo = new PageInfo(list);
        versionPageInfo.setList(listDto);
        return AjaxResult.success(versionPageInfo);
    }

    private VersionDto objectToDto(Version version) {
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
     * @param versionStr
     * @return
     */
    @RequestMapping(value = {"admin/version"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改版本", httpMethod = "POST", notes = "新增或修改版本")
    public AjaxResult postVersion(@ApiParam(value = "版本对象") @RequestBody String versionStr) {
        return addOrUpdate(versionStr);
    }

    private AjaxResult addOrUpdate(String versionStr) {
        Version version = JSON.parseObject(versionStr, Version.class);
        if (StringUtils.isNullOrEmpty(version.getUrl())) {
            return AjaxResult.failed("请上传sdk文件");
        }
        //修改
        if (version.getId() != null) {
            Version versionDb = versionService.getById(version.getId());
            if (versionDb != null) {
                versionDb.setVersionCode(version.getVersionCode());
                versionDb.setDescription(version.getDescription());
                versionDb.setUpdateTime(new Date());
                versionDb.setUrl(version.getUrl());
                versionService.updateVersion(versionDb);
                return AjaxResult.success(objectToDto(versionDb));
            } else {
                return AjaxResult.failed();
            }
        } else {
            //拿到extendedVersionCode 去数据库查这个对应值
            //ps:extendedVersionCode这个字段其实是表的ID
            String extendId = version.getExtendedVersionCode();
            if (!StringUtils.isNullOrEmpty(extendId)) {
                version = versionService.copyVersion(Long.valueOf(extendId), version);
                return AjaxResult.success(version);
            }
            version.setCreateTime(new Date());
            version.setExtendedVersionCode(null);
            versionService.saveVersion(version);
            return AjaxResult.success(objectToDto(version));
        }

    }

    /**
     * 获取单个版本
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"admin/version/{id}"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取单个版本", httpMethod = "GET", notes = "获取单个版本")
    public AjaxResult getVersion(@ApiParam(value = "版本ID") @PathVariable Long id) {
        Version version = versionService.getById(id);
        VersionDto versionDto = objectToDto(version);
        return AjaxResult.success(versionDto);
    }

    /**
     * 修改版本
     *
     * @param id
     * @param versionStr
     * @return
     */
    @RequestMapping(value = {"admin/version/{id}"}, method = RequestMethod.PUT)
    @ApiOperation(value = "修改版本", httpMethod = "PUT", notes = "修改版本")
    public AjaxResult putVersion(@ApiParam(value = "版本ID") @PathVariable Long id, @ApiParam(value = "版本对象") @RequestBody String versionStr) {
        Version version = JSON.parseObject(versionStr, Version.class);
        if (StringUtils.isNullOrEmpty(version.getUrl())) {
            return AjaxResult.failed("请上传sdk文件");
        }
        Version versionDb = versionService.getById(id);
        if (versionDb != null) {
//            String extendId = version.getExtendedVersionCode();
//            if (!StringUtils.isNullOrEmpty(extendId)) {
//                Version versionNew = versionService.copyVersion(Long.valueOf(extendId), version.getVersionCode(), version.getDescription());
//                return AjaxResult.success(versionNew);
//            }
            versionDb.setVersionCode(version.getVersionCode());
            versionDb.setDescription(version.getDescription());
            versionDb.setUpdateTime(new Date());
            versionService.updateVersion(versionDb);
            return AjaxResult.success(objectToDto(versionDb));
        } else {
            return AjaxResult.failed();
        }

    }

    /**
     * 删除版本
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"admin/version/{id}"}, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除版本", httpMethod = "DELETE", notes = "删除版本")
    public AjaxResult deleteVersion(@ApiParam(value = "版本ID") @PathVariable Long id) {
        try {
            versionService.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("不能刪除有绑定菜单或SDK的版本", e);
            return AjaxResult.failed("不能刪除有绑定菜单或SDK的版本");
        } finally {
        }
        return AjaxResult.success();
    }

}
