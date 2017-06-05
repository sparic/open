package cn.muye.shiro.mapper;

import cn.muye.shiro.domain.RolePermission;

import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/16.
 */
public interface AdminRolePermissionMapper {

    void save(RolePermission rolePermission);

    void update(RolePermission rolePermission);

    List<RolePermission> listPermissionsByRoleId(Long id);

    void bindRolePermission(Map map);

    void deleteByRoleId(Long roleId);
}
