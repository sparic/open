package cn.muye.version.controller;

import cn.muye.core.AjaxResult;
import cn.muye.config.CustomProperties;
import cn.muye.version.dto.VersionDto;
import cn.muye.version.domain.Version;
import cn.muye.version.service.AdminVersionService;
import cn.muye.version.service.VersionService;
import cn.muye.utils.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
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
public class VersionController {

    private static Logger LOGGER = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    private VersionService versionService;

    /**
     * 前台查询版本列表接口
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = {"/version"}, method = RequestMethod.GET)
    @RequiresPermissions("version:query")
    @ApiOperation(value = "前台查询版本列表", httpMethod = "GET", notes = "前台查询版本列表")
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

}
