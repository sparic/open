package cn.muye.shiro.service;

import cn.muye.shiro.domain.Permission;
import cn.muye.shiro.domain.Role;
import cn.muye.shiro.domain.RolePermission;
import cn.muye.shiro.domain.UserRole;
import cn.muye.shiro.mapper.PermissionMapper;
import cn.muye.shiro.mapper.RoleMapper;
import cn.muye.shiro.mapper.RolePermissionMapper;
import cn.muye.shiro.mapper.UserRoleMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/16.
 */
@Service
@Transactional
public class ShiroService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public List<Role> listRoles() {
        return roleMapper.listRole();
    }

    public Role getRoleByRoleId(Long id) {
        return roleMapper.getById(id);
    }

    public List<Permission> listPermissions(Integer page) {
        return permissionMapper.listPermissions(page);
    }

    public List<UserRole> listUserRolesByUserId(Long id) {
        return userRoleMapper.listUserRolesByUserId(id);
    }

    public List<Permission> listPermissionsByRoleId(Long id) {
        List<RolePermission> rolePermissionList = rolePermissionMapper.listPermissionsByRoleId(id);
        List<Long> permissionIdList = Lists.newArrayList();
        List<Permission> permissionList = null;
        if (rolePermissionList != null && rolePermissionList.size() > 0) {
            for (RolePermission rolePermission : rolePermissionList) {
                permissionIdList.add(rolePermission.getpId());
            }
            permissionList = permissionMapper.listPermissionsByIds(permissionIdList);
        }
        return permissionList;
    }

    public Role getById(Long id) {
        return roleMapper.getById(id);
    }

    public void update(Role roleDb) {
        roleMapper.update(roleDb);
    }

    public void save(Role role) {
        roleMapper.save(role);
    }

    public void bindRolePermission(Long roleId, List<Long> permissionIdList) {
        rolePermissionMapper.deleteByRoleId(roleId);
        if (permissionIdList != null && permissionIdList.size() > 0) {
            for (Long permissionId : permissionIdList) {
                Map map = Maps.newHashMap();
                map.put("roleId", roleId);
                map.put("permissionId", permissionId);
                rolePermissionMapper.bindRolePermission(map);
            }
        }
    }

    public void bindUserRole(Long roleId, Long userId) {
        userRoleMapper.deleteByUserId(userId);
        if (roleId != null) {
            Map map = Maps.newHashMap();
            map.put("roleId", roleId);
            map.put("userId", userId);
            userRoleMapper.bindUserRole(map);
        }
    }
}
