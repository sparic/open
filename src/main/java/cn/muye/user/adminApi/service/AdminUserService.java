package cn.muye.user.adminApi.service;

import cn.muye.shiro.service.AdminShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.adminApi.mapper.AdminUserMapper;
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
public class AdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;

	@Autowired
	private AdminShiroService adminShiroService;

	public User getUserById(Long id) {
		return adminUserMapper.getUserById(id);
	}

	public void saveAndBindRole(User user) {
		adminUserMapper.save(user);
		adminShiroService.bindUserRole(user.getUserRoleId(), user.getId());
	}

	public void updateAndBindRole(User user) {
		adminUserMapper.update(user);
		adminShiroService.bindUserRole(user.getUserRoleId(), user.getId());
	}

	public User getByName(String userName) {
		return adminUserMapper.getUserByName(userName);
	}

	public List<User> getUserList(Integer page) {
		return adminUserMapper.getUserList(page);
	}

	public void deActivateById(Long id) {
		adminUserMapper.deleteById(id);
	}

	public User checkAdminLogin(User user) {
		return adminUserMapper.checkAdminLogin(user);
	}

	public int updateUser(User user) {
		return adminUserMapper.update(user);
	}

	public User getByBizId(String bizId) {
		return adminUserMapper.getByBizId(bizId);
	}

	public User getByCompany(String company) {
		return adminUserMapper.getByCompany(company);
	}

	public User getByEmail(String emailAddress) {
		return adminUserMapper.getByEmail(emailAddress);
	}
}
