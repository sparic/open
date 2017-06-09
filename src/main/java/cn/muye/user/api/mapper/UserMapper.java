package cn.muye.user.api.mapper;

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
public interface UserMapper {

	void save(User user);

	User getUserById(Long id);

	User getUserByName(String userName);

	User checkCustomerLogin(User user);
}
