package cn.muye.shiro.mapper;

import cn.muye.shiro.domain.Role;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/16.
 */
public interface AdminRoleMapper {

    void save(Role role);

    void update(Role role);

    List<Role> listRole();

    Role getById(Long id);
}
