package cn.muye.shiro.controller;

import cn.muye.core.AjaxResult;
import cn.muye.shiro.domain.Permission;
import cn.muye.shiro.domain.Role;
import cn.muye.shiro.service.AdminShiroService;
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
public class AdminShiroController {

    @Autowired
    private AdminShiroService adminShiroService;

    @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
    @RequiresPermissions("role:query")
    @ResponseBody
    @ApiOperation(value = "后台查询角色列表", httpMethod = "GET", notes = "后台查询角色列表")
    public AjaxResult listRoles() {
        List<Role> roleList = adminShiroService.listRoles();
        return AjaxResult.success(roleList, "查询成功");
    }

    @RequestMapping(value = "/admin/role", method = RequestMethod.POST)
    @ApiOperation(value = "后台新增/修改角色", httpMethod = "POST", notes = "后台新增/修改角色")
    @RequiresPermissions("role:upsert")
    @ResponseBody
    public AjaxResult postRole(@ApiParam(value = "角色对象") @RequestBody Role role) {
        if (role != null && (role.getEnName() == null || role.getCnName() == null)) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "信息不全，请完善后再提交");
        }
        return post(role);
    }

    private AjaxResult post(Role role) {
        if (role.getId() != null) {
            Role roleDb = adminShiroService.getById(role.getId());
            if (roleDb != null) {
                roleDb.setEnName(role.getEnName());
                roleDb.setCnName(role.getCnName());
                adminShiroService.update(roleDb);
                return AjaxResult.success(roleDb, "修改成功");
            } else {
                return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不存在该角色");
            }
        } else {
            adminShiroService.save(role);
            return AjaxResult.success(role, "新增成功");
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
    @ApiOperation(value = "后台角色绑定权限", httpMethod = "POST", notes = "后台角色绑定权限")
    @RequiresPermissions("role:upsert")
    @ResponseBody
    public AjaxResult bindPermissionAdmin(@ApiParam(value = "权限id集合(逗号分隔)") @RequestParam String permissionIdListStr, @ApiParam(value = "角色id") @RequestParam Long roleId) {
        List<Long> permissionIdList = JSON.parseArray(permissionIdListStr, Long.class);
        if (permissionIdList != null && permissionIdList.size() > 0) {
            adminShiroService.bindRolePermission(roleId, permissionIdList);
        }
        return AjaxResult.success(null, "绑定成功");
    }

    @RequestMapping(value = "/admin/permission", method = RequestMethod.GET)
    @ApiOperation(value = "后台查询权限列表", httpMethod = "GET", notes = "后台查询权限列表")
    @RequiresPermissions("permission:query")
    @ResponseBody
    public AjaxResult listPermissionsAdmin(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                      @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize, true, null, true);
        List<Permission> permissionList = adminShiroService.listPermissions(page);
        PageInfo<Permission> permissionPageInfo = new PageInfo(permissionList);
        permissionPageInfo.setList(permissionList);
        return AjaxResult.success(permissionPageInfo, "查询成功");
    }


    @RequestMapping(value = "/admin/permission/{roleId}", method = RequestMethod.GET)
    @ApiOperation(value = "后台查询角色已绑定权限", httpMethod = "GET", notes = "后台查询角色已绑定权限")
    @RequiresPermissions("permission:query")
    @ResponseBody
    public AjaxResult listPermissionsByRoleIdAdmin(@ApiParam(value = "角色ID") @PathVariable String roleId) {
        List<Permission> permissionList = adminShiroService.listPermissionsByRoleId(Long.valueOf(roleId));
        return AjaxResult.success(permissionList, "绑定成功");
    }

}
