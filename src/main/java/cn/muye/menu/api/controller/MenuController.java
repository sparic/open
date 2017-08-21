package cn.muye.menu.api.controller;

import cn.muye.core.AjaxResult;
import cn.muye.menu.dto.MenuDto;
import cn.muye.menu.domain.Menu;
import cn.muye.resource.domain.ApiPackage;
import cn.muye.resource.service.ApiPackageService;
import cn.muye.version.domain.Version;
import cn.muye.menu.api.service.MenuService;
import cn.muye.version.api.service.VersionService;
import cn.muye.utils.DateTimeUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
@RestController
public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private VersionService versionService;

    private static List<MenuDto> menuDtoList;

    @Autowired
    private ApiPackageService apiPackageService;

    /**
     * 前台查询菜单列表接口
     *
     * @param versionId
     * @return
     */
    @RequestMapping(value = {"/menu"}, method = RequestMethod.GET)
    @RequiresPermissions("menu:query")
    @ApiOperation(value = "前台查询菜单列表", httpMethod = "GET", notes = "前台查询菜单列表")
    public AjaxResult customerGetMenuList(@ApiParam(value = "版本号") @RequestParam(value = "versionId", required = false) Long versionId) {
        List<Menu> menuList = null;
        if (versionId == null) {
            List<Version> list = versionService.listVersions();
            Version version = list.get(0);
            menuList = menuService.getByVersionId(version.getId());
        } else {
            menuList = menuService.listMenus(versionId);
        }
        menuDtoList = Lists.newArrayList();
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                menuDtoList.add(objectToDto(menu));
            }
        }
        Version versionDb = versionService.getById(versionId);
        //扔给前端的最上层菜单
        Map map = Maps.newHashMap();
        List<MenuDto> menuDtoNewList = Lists.newArrayList();
        if (menuDtoList != null && menuDtoList.size() > 0) {
            for (MenuDto menuDto : menuDtoList) {
                menuDto.setContent(null);
                getMenu(menuDto);
                if (menuDto.getParentId() == null || menuDto.getParentId().equals("null")) {
                    menuDtoNewList.add(menuDto);
                }
            }
        }
        List<ApiPackage> apiPackageList = apiPackageService.list();
        map.put("menuList", menuDtoNewList);
        map.put("version", versionDb);
        map.put("apiPackageList", apiPackageList);
        return AjaxResult.success(map, "查询成功");
    }

    private MenuDto objectToDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId() == null ? "" : String.valueOf(menu.getId()));
        menuDto.setUpdateTime(DateTimeUtils.getDateString(menu.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        menuDto.setCreateTime(DateTimeUtils.getDateString(menu.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        menuDto.setContent(menu.getContent() == null ? "" : menu.getContent());
        menuDto.setUrl(menu.getUrl() == null ? "" : menu.getUrl());
        menuDto.setIsValid(menu.getIsValid());
        menuDto.setIsLeaf(menu.getIsLeaf());
        menuDto.setOriginId(menu.getOriginId() == null ? "" : String.valueOf(menu.getOriginId()));
        menuDto.setName(menu.getName());
        menuDto.setParentId(String.valueOf(menu.getParentId()));
        menuDto.setVersionId(menu.getVersionId() == null ? "" : String.valueOf(menu.getVersionId()));
        return menuDto;
    }

    /**
     * 获取某个menu所有子menu
     *
     * @param menuDto
     * @return
     */
    private static List<MenuDto> getChildrenMenu(MenuDto menuDto) {
        List<MenuDto> menuChildren = Lists.newArrayList();
        if (menuDtoList != null && menuDtoList.size() > 0) {
            for (MenuDto m : menuDtoList) {
                Long parentId = m.getParentId() == null || m.getParentId().equals("null") ? null : Long.valueOf(m.getParentId());
                String parentIdStr = String.valueOf(parentId);
                if (parentIdStr != null && parentIdStr.equals(menuDto.getOriginId())) {
                    menuChildren.add(m);
                }
            }
        }
        return menuChildren;
    }

    /**
     * 组装MenuDto的menuDtoChildren
     *
     * @param menuDto
     */
    private void getMenu(MenuDto menuDto) {
        List<MenuDto> childrenMenuDtoList = getChildrenMenu(menuDto);
        if (childrenMenuDtoList != null && childrenMenuDtoList.size() > 0) {
            menuDto.setChildren(childrenMenuDtoList);
            for (MenuDto m : childrenMenuDtoList) {
                getMenu(m);
            }
        }
    }

    /**
     * 获取单个菜单下的子菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"/menu/{id}"}, method = RequestMethod.GET)
    @RequiresPermissions("menu:query")
    @ApiOperation(value = "前台获取单个菜单详情", httpMethod = "GET", notes = "前台获取单个菜单详情")
    public AjaxResult getMenu(@ApiParam(value = "菜单ID") @PathVariable String id) {
        try {
            Menu menu = menuService.getById(Long.valueOf(id));
            return AjaxResult.success(objectToDto(menu), "查询成功");
        } catch (Exception e) {
            LOGGER.error("{}", e);
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该条记录");
        }
    }

    /**
     * 修改菜单
     * todo spring传put接不到参数 网上有方案解决
     * @param id
     * @param menu
     * @return
     */
//    @RequestMapping(value = "admin/menu/{id}", method = RequestMethod.PUT)
//    @ApiOperation(value = "修改菜单", httpMethod = "PUT", notes = "修改菜单")
//    public AjaxResult putMenu(@ApiParam(value = "菜单ID") @PathVariable Long id, @ApiParam(value = "菜单对象") @RequestBody Menu menu) {
//        Menu menuDb = menuService.getById(id);
//        menuDb.setName(menu.getName());
//        menuDb.setParentId(menu.getParentId());
//        menuDb.setUpdateTime(new Date());
//        menuDb.setContent(menu.getContent());
//        menuDb.setUrl(menu.getUrl());
//        menuService.updateMenu(menuDb);
//        return AjaxResult.success(objectToDto(menuDb));
//    }

}
