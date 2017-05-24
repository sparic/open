package cn.muye.user.service;

import cn.muye.shiro.service.ShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.mapper.UserMapper;
import cn.muye.utils.MailUtil;
import com.google.common.collect.Lists;
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

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private ShiroService shiroService;

	public User getUserById(Long id) {
		return userMapper.getUserById(id);
	}

	public void saveAndBindRole(User user) {
		userMapper.save(user);
		shiroService.bindUserRole(user.getUserRoleId(), user.getId());
	}

	public void updateAndBindRole(User user) {
		userMapper.update(user);
		shiroService.bindUserRole(user.getUserRoleId(), user.getId());
	}

	public User getUserByName(String userName) {
		return userMapper.getUserByName(userName);
	}

	public List<User> getUserList(Integer page) {
		return userMapper.getUserList(page);
	}

	public User getUserInfoByUserPhone(String userPhone) {
		return userMapper.getUserInfoByUserPhone(userPhone);
	}

	public void deleteById(Long id) {
		userMapper.deleteById(id);
	}

}
