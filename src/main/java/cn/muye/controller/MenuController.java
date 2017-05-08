package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.dto.MenuDto;
import cn.muye.model.Menu;
import cn.muye.service.MenuService;
import cn.muye.util.DateTimeUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表接口
     *
     * @param page
     * @param pageSize
     * @param versionId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询菜单列表", httpMethod = "GET", notes = "查询菜单列表")
    public AjaxResult getMenuList(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @ApiParam(value = "版本ID") @RequestParam(value = "versionId", required = true) Long versionId) {
        PageHelper.startPage(page, pageSize);
        List<Menu> menuList = menuService.listMenus(page, versionId);
        List<MenuDto> menuDtoList = Lists.newArrayList();
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                menuDtoList.add(objectToDto(menu));
            }
        }
        PageInfo<MenuDto> menuPageInfo = new PageInfo(menuDtoList);
        menuPageInfo.setList(menuDtoList);
        return AjaxResult.success(menuPageInfo);
    }

    private MenuDto objectToDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setUpdateTime(DateTimeUtils.getDateString(menu.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        menuDto.setCreateTime(DateTimeUtils.getDateString(menu.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        menuDto.setContent(menu.getContent());
        menuDto.setUrl(menu.getUrl());
        menuDto.setIsValid(menu.getIsValid());
        menuDto.setIsLeaf(menu.getIsLeaf());
        menuDto.setOriginId(menu.getOriginId());
        menuDto.setName(menu.getName());
        menuDto.setParentId(menu.getParentId());
        menuDto.setVersionId(menu.getVersionId());
        return menuDto;
    }

    /**
     * 获取某个menu所有子menu
     * @param menuDto
     * @return
     */
//    private static List<MenuDto> getChildrenMenu(MenuDto menuDto) {
//        List<MenuDto> menuChildren = Lists.newArrayList();
//        if (menuDtoList != null && menuDtoList.size() > 0) {
//            for (MenuDto m : menuDtoList) {
//                Long parentId = m.getParentId();
//                if (parentId != null && parentId.equals(menuDto.getId())) {
//                    menuChildren.add(m);
//                }
//            }
//        }
//        return menuChildren;
//    }

    /**
     * 组装MenuDto的menuDtoChildren
     * @param menuDto
     */
//    private void getMenu(MenuDto menuDto) {
//        List<MenuDto> childrenMenuDtoList = getChildrenMenu(menuDto);
//        if (!menuDto.getIsLeaf()) {
//            menuDto.setChildrenMenuDtoList(childrenMenuDtoList);
//            for (MenuDto m : childrenMenuDtoList) {
//                getMenu(m);
//            }
//        } else {
//            Document document = documentService.getByMenuId(menuDto.getId());
//            menuDto.setDocumentId(document.getId());
//        }
//    }

    /**
     * 新增接口
     *
     * @param menuStr
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增菜单", httpMethod = "POST", notes = "新增菜单")
    public AjaxResult postMenu(@ApiParam(value = "菜单对象") @RequestBody String menuStr) {
        return post(menuStr);
    }

    private AjaxResult post(String menuStr) {
        Menu menu = JSON.parseObject(menuStr, Menu.class);
        if (menu.getId() != null) {
            Menu menuDb = menuService.getById(menu.getId());
            if (menuDb != null) {
                menuDb.setName(menu.getName());
                menuDb.setParentId(menu.getParentId());
                menuDb.setUpdateTime(new Date());
                menuDb.setContent(menu.getContent());
                menuDb.setUrl(menu.getUrl());
                menuService.updateMenu(menuDb);
                return AjaxResult.success(objectToDto(menuDb));
            } else {
                return AjaxResult.failed();
            }
        } else {
            menu.setCreateTime(new Date());
            menuService.saveMenu(menu);
            menu.setOriginId(menu.getId());
            menuService.updateMenu(menu);
            return AjaxResult.success(objectToDto(menu));
        }

    }

    /**
     * 获取单个菜单下的子菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取单个菜单下的子菜单", httpMethod = "GET", notes = "获取单个菜单下的子菜单")
    public AjaxResult getMenu(@ApiParam(value = "菜单ID") @PathVariable Long id) {
        List<Menu> menuList = menuService.getByPid(id);
        return AjaxResult.success(menuList);
    }

    /**
     * 修改菜单
     *
     * @param id
     * @param menu
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "修改菜单", httpMethod = "PUT", notes = "修改菜单")
    public AjaxResult putMenu(@ApiParam(value = "菜单ID") @PathVariable Long id, @ApiParam(value = "菜单对象") @RequestBody Menu menu) {
        Menu menuDb = menuService.getById(id);
        menuDb.setName(menu.getName());
        menuDb.setParentId(menu.getParentId());
        menuDb.setUpdateTime(new Date());
        menuDb.setContent(menu.getContent());
        menuDb.setUrl(menu.getUrl());
        menuService.updateMenu(menuDb);
        return AjaxResult.success(objectToDto(menuDb));
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除菜单", httpMethod = "DELETE", notes = "删除菜单")
    public AjaxResult deleteMenu(@ApiParam(value = "菜单ID") @PathVariable Long id) {
        List<Menu> childrenMenu = menuService.listMenusByParentId(id);
        if (childrenMenu != null && childrenMenu.size() > 0) {
            return AjaxResult.failed("不能删除有子菜单的父菜单");
        } else {
            menuService.deleteById(id);
            return AjaxResult.success();
        }
    }

}
