package cn.muye.shiro.controller;

import cn.muye.core.AjaxResult;
import cn.muye.shiro.domain.Permission;
import cn.muye.shiro.domain.Role;
import cn.muye.shiro.service.ShiroService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/17.
 */
@Controller
public class ShiroController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
    @RequiresPermissions("role:query")
    @ResponseBody
    @ApiOperation(value = "查询角色列表", httpMethod = "GET", notes = "查询角色列表")
    public AjaxResult listRoles() {
        List<Role> roleList = shiroService.listRoles();
        return AjaxResult.success(roleList);
    }

    @RequestMapping(value = "/admin/role", method = RequestMethod.POST)
    @ApiOperation(value = "新增/修改角色", httpMethod = "POST", notes = "新增/修改角色")
    @RequiresPermissions("role:upsert")
    @ResponseBody
    public AjaxResult postRole(@ApiParam(value = "角色对象") @RequestBody Role role) {
        return post(role);
    }

    private AjaxResult post(Role role) {
        if (role.getId() != null) {
            Role roleDb = shiroService.getById(role.getId());
            if (roleDb != null) {
                roleDb.setEnName(role.getEnName());
                roleDb.setCnName(role.getCnName());
                shiroService.update(roleDb);
                return AjaxResult.success(roleDb);
            } else {
                return AjaxResult.failed("不存在该角色");
            }
        } else {
            shiroService.save(role);
            return AjaxResult.success(role);
        }
    }

    /**
     * 角色绑定权限
     *
     * @param permissionIdListStr
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/admin/role/permission", method = RequestMethod.POST)
    @ApiOperation(value = "角色绑定权限", httpMethod = "POST", notes = "角色绑定权限")
    @RequiresPermissions("role:upsert")
    @ResponseBody
    public AjaxResult bindPermission(@ApiParam(value = "权限id集合(逗号分隔)") @RequestParam String permissionIdListStr, @ApiParam(value = "角色id") @RequestParam Long roleId) {
        List<Long> permissionIdList = JSON.parseArray(permissionIdListStr, Long.class);
        if (permissionIdList != null && permissionIdList.size() > 0) {
            shiroService.bindRolePermission(roleId, permissionIdList);
        }
        return AjaxResult.success("绑定成功");
    }

    @RequestMapping(value = "/admin/permission", method = RequestMethod.GET)
    @ApiOperation(value = "查询权限列表", httpMethod = "GET", notes = "查询权限列表")
    @RequiresPermissions("permission:query")
    @ResponseBody
    public AjaxResult listPermissions(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                      @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Permission> permissionList = shiroService.listPermissions(page);
        PageInfo<Permission> permissionPageInfo = new PageInfo(permissionList);
        permissionPageInfo.setList(permissionList);
        return AjaxResult.success(permissionPageInfo);
    }


    @RequestMapping(value = "/admin/permission/{roleId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询角色已绑定权限", httpMethod = "GET", notes = "查询角色已绑定权限")
    @RequiresPermissions("permission:query")
    @ResponseBody
    public AjaxResult listPermissionsByRoleId(@ApiParam(value = "角色ID") @PathVariable String roleId) {
        List<Permission> permissionList = shiroService.listPermissionsByRoleId(Long.valueOf(roleId));
        return AjaxResult.success(permissionList);
    }

}
