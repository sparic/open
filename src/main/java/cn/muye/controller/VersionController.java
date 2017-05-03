package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.model.Menu;
import cn.muye.model.Version;
import cn.muye.service.MenuService;
import cn.muye.service.VersionService;
import com.github.pagehelper.PageHelper;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/2.
 */
@RestController
@RequestMapping("/version")
public class VersionController {

    private static final Logger LOGGER = Logger.getLogger(VersionController.class);

    @Autowired
    private VersionService versionService;

    @Autowired
    private MenuService menuService;

    /**
     * 查询版本列表接口
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "查询版本列表", httpMethod = "GET", notes = "查询版本列表")
    public AjaxResult getVersionList(@ApiParam(value = "页号")@RequestParam(value = "page", required = false) Integer page,
                                     @ApiParam(value = "每页记录数")@RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            PageHelper.startPage(page, pageSize);
        }
        List<Version> list = versionService.listVersions();
        return AjaxResult.success(list);
    }

    /**
     * 新增版本接口
     *
     * @param version
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "新增版本", httpMethod = "POST", notes = "新增版本列表")
    public AjaxResult postVersion(@ApiParam(value = "版本对象")@RequestBody Version version) {
        return post(version);
    }

    private AjaxResult post(Version version) {
        version.setCreateTime(new Date());
        versionService.saveVersion(version);
        return AjaxResult.success(version);
    }

    /**
     * 获取单个版本
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取单个版本", httpMethod = "GET", notes = "获取单个版本")
    public AjaxResult getVersion(@ApiParam(value = "版本ID")@PathVariable Long id) {
        Version version = versionService.getById(id);
        return AjaxResult.success(version);
    }

    /**
     * 修改版本
     *
     * @param id
     * @param version
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "修改版本", httpMethod = "PUT", notes = "修改版本")
    public AjaxResult putVersion(@ApiParam(value = "版本ID")@PathVariable Long id, @ApiParam(value = "版本对象")@RequestBody Version version) {
        Version versionDb = versionService.getById(id);
        if (versionDb != null) {
            versionDb.setVersionCode(version.getVersionCode());
            versionDb.setDescription(version.getDescription());
            versionDb.setUpdateTime(new Date());
            versionService.updateVersion(versionDb);
            return AjaxResult.success(versionDb);
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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除版本", httpMethod = "DELETE", notes = "删除版本")
    public AjaxResult deleteVersion(@ApiParam(value = "版本ID")@PathVariable Long id) {
        versionService.deleteById(id);
        return AjaxResult.success();
    }

    /**
     * 拷贝版本
     *
     * @param versionId
     * @return
     */
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    @ApiOperation(value = "拷贝版本", httpMethod = "POST", notes = "拷贝版本")
    public AjaxResult copyVersion(@ApiParam(value = "版本ID")@RequestParam(value = "versionId", required = true) Long versionId,
                                  @ApiParam(value = "版本编号")@RequestParam(value = "versionCode", required = true) String versionCode,
                                  @ApiParam(value = "版本描述")@RequestParam(value = "description", required = false) String description) {
        Version versionNew = new Version();
        versionNew.setDescription(description);
        versionNew.setVersionCode(versionCode);
        versionNew.setCreateTime(new Date());
        versionService.saveVersion(versionNew);
        List<Menu> menuList = menuService.getByVersionId(versionId);
        if (menuList != null && menuList.size() > 0) {
            for (Menu m : menuList) {
                Menu newMenu = new Menu();
                newMenu.setVersionId(versionNew.getId());
                newMenu.setIsLeaf(m.getIsLeaf());
                newMenu.setCreateTime(new Date());
                newMenu.setIsValid(m.getIsValid());
                newMenu.setParentId(m.getParentId());
                newMenu.setName(m.getName());
                newMenu.setOriginId(m.getOriginId());
                newMenu.setContent(m.getContent());
                newMenu.setUrl(m.getUrl());
                menuService.saveMenu(newMenu);
            }
        }
        return AjaxResult.success(versionNew);
    }
}
