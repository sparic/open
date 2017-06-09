package cn.muye.shiro.mapper;

import cn.muye.shiro.domain.Role;
import cn.muye.shiro.domain.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/16.
 */
public interface AdminUserRoleMapper {

    List<UserRole> listUserRolesByUserId(Long id);

    void bindUserRole(Map map);

    void deleteByUserId(Long userId);
}
