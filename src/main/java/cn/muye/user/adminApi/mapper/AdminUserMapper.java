package cn.muye.user.adminApi.mapper;

import cn.muye.user.domain.User;

import java.util.List;
import java.util.Map;

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

	int update(User user);

	User getUserById(Long id);

	User getUserByName(String userName);

	List<User> getUserList(Integer page);

	void deleteById(Long id);

	User checkAdminLogin(User user);

	User getByBizId(String bizId);

    User getByCompany(String company);

	User getByEmail(String emailAddress);
}
