package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.model.Menu;
import cn.muye.model.Version;
import cn.muye.service.DocumentService;
import cn.muye.service.MenuService;
import cn.muye.service.VersionService;
import com.github.pagehelper.PageHelper;
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
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public AjaxResult getVersionList(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            PageHelper.startPage(page, pageSize);
        }
        List<Version> list = versionService.listVersions();
        return AjaxResult.success(list);
    }

    /**
     * 新增接口
     * @param version
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public AjaxResult postVersion(@RequestBody Version version) {
        return post(version);
    }


    private AjaxResult post(Version version) {
        version.setCreateTime(new Date());
        versionService.saveVersion(version);
        return AjaxResult.success(version);
    }

    /**
     * 获取单个版本
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult getVersion(@PathVariable Long id) {
        Version version = versionService.getById(id);
        return AjaxResult.success(version);
    }

    /**
     * 修改版本
     * @param id
     * @param version
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AjaxResult putVersion(@PathVariable Long id, @RequestBody Version version) {
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
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult deleteVersion(@PathVariable Long id) {
        versionService.deleteById(id);
        return AjaxResult.success();
    }

    /**
     * 拷贝版本
     * @param versionId
     * @return
     */
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public AjaxResult copyVersion(@RequestParam(value = "versionId",required = true) Long versionId,
                                  @RequestParam(value = "versionCode",required = true) String versionCode,
                                  @RequestParam(value = "description",required = false) String description) {
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
