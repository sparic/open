package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.model.Menu;
import cn.muye.service.MenuService;
import com.github.pagehelper.PageHelper;
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

//    @Autowired
//    private DocumentService documentService;

//    private static List<MenuDto> menuDtoList;

    /**
     * 查询菜单列表接口
     *
     * @param page
     * @param pageSize
     * @param versionId
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "查询菜单列表", httpMethod = "GET", notes = "查询菜单列表")
    public AjaxResult getMenuList(@ApiParam(value = "页号")@RequestParam(value = "page", required = false) Integer page,
                                  @ApiParam(value = "每页记录数")@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                  @ApiParam(value = "版本ID")@RequestParam(value = "versionId", required = true) Long versionId) {
        if (page == null && pageSize == null) {
            List<Menu> menuList = menuService.listMenus(null, versionId);
            return AjaxResult.success(menuList);
        } else {
            PageHelper.startPage(page, pageSize);
            List<Menu> list = menuService.listMenus(page, versionId);
            return AjaxResult.success(list);
        }
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
     * @param menu
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "新增菜单", httpMethod = "POST", notes = "新增菜单")
    public AjaxResult postMenu(@ApiParam(value = "菜单对象")@RequestBody Menu menu) {
        return post(menu);
    }

    private AjaxResult post(Menu menu) {
        menu.setCreateTime(new Date());
        menuService.saveMenu(menu);
        menu.setOriginId(menu.getId());
        menuService.updateMenu(menu);
        return AjaxResult.success(menu);
    }

    /**
     * 获取单个菜单下的子菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取单个菜单下的子菜单", httpMethod = "GET", notes = "获取单个菜单下的子菜单")
    public AjaxResult getMenu(@ApiParam(value = "菜单ID")@PathVariable Long id) {
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "修改菜单", httpMethod = "PUT", notes = "修改菜单")
    public AjaxResult putMenu(@ApiParam(value = "菜单ID")@PathVariable Long id, @ApiParam(value = "菜单对象")@RequestBody Menu menu) {
        Menu menuDb = menuService.getById(id);
        menuDb.setName(menu.getName());
        menuDb.setParentId(menu.getParentId());
        menuDb.setUpdateTime(new Date());
        menuDb.setContent(menu.getContent());
        menuDb.setUrl(menu.getUrl());
        menuService.updateMenu(menuDb);
        return AjaxResult.success(menuDb);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除菜单", httpMethod = "DELETE", notes = "删除菜单")
    public AjaxResult deleteMenu(@ApiParam(value = "菜单ID")@PathVariable Long id) {
        List<Menu> childrenMenu = menuService.listMenusByParentId(id);
        if (childrenMenu != null && childrenMenu.size() > 0) {
            return AjaxResult.failed("不能删除有子菜单的父菜单");
        } else {
            return AjaxResult.success();
        }
    }

}
