package cn.muye.login.service;

import cn.muye.login.domain.User;
import cn.muye.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/3
 * Time: 15:46
 * Describe:
 * Version:1.0
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUserById(Long id) {
		return userMapper.getUserById(id);
	}

	public void save(User user) {
		userMapper.save(user);
	}

	public void update(User user) {
		 userMapper.update(user);
	}

	public User getUserByName(String userName) {
		return userMapper.getUserByName(userName);
	}

	public List<User> getUserList(Integer page) {
		return userMapper.getUserList(page);
	}
}
