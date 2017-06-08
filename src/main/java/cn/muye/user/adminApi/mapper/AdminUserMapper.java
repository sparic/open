package cn.muye.user.adminApi.mapper;

import cn.muye.user.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/3
 * Time: 14:38
 * Describe: 用户
 * Version:1.0
 */
public interface AdminUserMapper {

	void save(User user);

	void update(User user);

	User getUserById(Long id);

	User getUserByName(String userName);

	List<User> getUserList(Integer page);

	void deleteById(Long id);

	void bindUserRole(Long roleId, Long userId);

	User checkAdminLogin(User user);

}
