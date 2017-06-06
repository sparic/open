package cn.muye.user.adminApi.service;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.adminApi.service.AdminAgentApplyService;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.shiro.service.AdminShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.adminApi.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

	@Autowired
	private AdminAgentApplyService adminAgentApplyService;

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

	public void update(User user) {
		adminUserMapper.update(user);
	}

	public User getUserByName(String userName) {
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

}
