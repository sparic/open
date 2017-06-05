package cn.muye.user.service;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.service.AdminAgentApplyService;
import cn.muye.cooperation.service.AgentApplyService;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.shiro.service.AdminShiroService;
import cn.muye.shiro.service.ShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.mapper.AdminUserMapper;
import cn.muye.user.mapper.UserMapper;
import cn.muye.utils.MailUtil;
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
		//添加代理商申请记录
		AgentApply agentApply = new AgentApply();
		agentApply.setUserId(user.getId());
		agentApply.setUrl(user.getUrl());
		agentApply.setCreateTime(new Date());
		agentApply.setStatus(ApplyStatusType.SUBMIT.getValue());
		adminAgentApplyService.save(agentApply);
	}

	/**
	 * 如果前台用户注册+申请代理商，就要添加代理商申请
	 * @param user
	 */
	public void saveAndApplyAgent(User user) {
		adminUserMapper.save(user);
		adminShiroService.bindUserRole(user.getUserRoleId(), user.getId());
		//添加代理商申请记录
		AgentApply agentApply = new AgentApply();
		agentApply.setUserId(user.getId());
		agentApply.setUrl(user.getUrl());
		agentApply.setDescription(user.getDescription());
		agentApply.setCreateTime(new Date());
		agentApply.setStatus(ApplyStatusType.SUBMIT.getValue());
		adminAgentApplyService.save(agentApply);
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

	public User getUserInfoByUserPhone(String userPhone) {
		return adminUserMapper.getUserInfoByUserPhone(userPhone);
	}

	public void deleteById(Long id) {
		adminUserMapper.deleteById(id);
	}

	public User checkAdminLogin(User user) {
		return adminUserMapper.checkAdminLogin(user);
	}

	public User checkCustomerLogin(User user) {
		return adminUserMapper.checkCustomerLogin(user);
	}
}
