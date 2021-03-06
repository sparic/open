package cn.muye.menu.adminApi.controller;

import cn.muye.core.AjaxResult;
import cn.muye.menu.domain.Menu;
import cn.muye.menu.dto.MenuDto;
import cn.muye.menu.adminApi.service.AdminMenuService;
import cn.muye.utils.DateTimeUtils;
import cn.muye.version.domain.Version;
import cn.muye.version.adminApi.service.AdminVersionService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
@RestController
public class AdminMenuController {

    private static final Logger LOGGER = Logger.getLogger(AdminMenuController.class);

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private AdminVersionService adminVersionService;

    private static List<MenuDto> menuDtoList;

    /**
     * 后台查询菜单列表接口
     *
     * @param versionId
     * @return
     */
    @RequestMapping(value = {"admin/menu"}, method = RequestMethod.GET)
    @RequiresPermissions("menu:query")
    @ApiOperation(value = "后台查询菜单列表", httpMethod = "GET", notes = "后台查询菜单列表")
    public AjaxResult adminGetMenuList(@ApiParam(value = "版本号") @RequestParam(value = "versionId", required = false) String versionId) {
        if (versionId == null || versionId.trim().length() == 0) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
        List<Menu> menuList = null;
        if (versionId == null) {
            List<Version> list = adminVersionService.listVersions();
            Version version = list.get(0);
            menuList = adminMenuService.getByVersionId(version.getId());
        } else {
            menuList = adminMenuService.listMenus(Long.valueOf(versionId));
        }
        menuDtoList = Lists.newArrayList();
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                menuDtoList.add(objectToDtoAdmin(menu));
            }
        }
        Version versionDb = adminVersionService.getById(Long.valueOf(versionId));
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
        map.put("menuList", menuDtoNewList);
        map.put("version", versionDb);
        return AjaxResult.success(map, "查询成功");
    }

    private MenuDto objectToDtoAdmin(Menu menu) {
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
     * 新增接口
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = {"admin/menu"}, method = RequestMethod.POST)
    @RequiresPermissions("menu:upsert")
    @ApiOperation(value = "后台新增/修改菜单", httpMethod = "POST", notes = "后台新增/修改菜单")
    public AjaxResult postMenuAdmin(@ApiParam(value = "菜单对象") @RequestBody Menu menu) {
        if (menu != null && (menu.getVersionId() == null || menu.getName() == null)) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "菜单信息不全，请完善");
        }
        return post(menu);
    }

    private AjaxResult post(Menu menu) {
        Long versionId = menu.getVersionId();
        Long id = menu.getId();
        if (versionId != null && !versionId.equals(0L)) {
            Version version = adminVersionService.getById(versionId);
            if (version == null) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "版本号为空或不存在");
            }
        }
        if (id != null) {
            Menu menuDb = adminMenuService.getById(id);
            Long pId = menu.getParentId();
            if (pId != null && (pId.equals(menuDb.getOriginId()) || pId.equals(menuDb.getId()))) {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不能选择自己做父菜单");
            }
            if (menuDb != null) {
                menuDb.setName(menu.getName());
                menuDb.setParentId(menu.getParentId());
                menuDb.setUpdateTime(new Date());
                menuDb.setContent(menu.getContent());
                menuDb.setUrl(menu.getUrl());
                adminMenuService.updateMenu(menuDb);
                return AjaxResult.success(objectToDtoAdmin(menuDb), "修改成功");
            } else {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在的文档");
            }
        } else {
            menu.setCreateTime(new Date());
            menu.setIsValid(true);
            adminMenuService.saveMenuAndUpdateOriginId(menu, "menu");
            return AjaxResult.success(objectToDtoAdmin(menu), "新增成功");
        }
    }

    /**
     * 后台获取单个菜单下的子菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"admin/menu/{id}"}, method = RequestMethod.GET)
    @RequiresPermissions("menu:query")
    @ApiOperation(value = "后台获取单个菜单详情", httpMethod = "GET", notes = "后台获取单个菜单详情")
    public AjaxResult getMenuAdmin(@ApiParam(value = "菜单ID") @PathVariable String id) {
        if (id == null || id.trim().length() == 0) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
        try {
            Menu menu = adminMenuService.getById(Long.valueOf(id));
            return AjaxResult.success(objectToDtoAdmin(menu), "查询成功");
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

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "admin/menu/{id}", method = RequestMethod.DELETE)
    @RequiresPermissions("menu:delete")
    @ApiOperation(value = "后台删除菜单", httpMethod = "DELETE", notes = "后台删除菜单")
    public AjaxResult deleteMenuAdmin(@ApiParam(value = "菜单ID") @PathVariable String id) {
        if (id == null || id.trim().length() == 0) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
        List<Menu> childrenMenu = adminMenuService.listMenusByParentId(Long.valueOf(id));
        if (childrenMenu != null && childrenMenu.size() > 0) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不能删除有子菜单的父菜单");
        } else {
            adminMenuService.deleteById(Long.valueOf(id));
            return AjaxResult.success("", "删除成功");
        }
    }

}
