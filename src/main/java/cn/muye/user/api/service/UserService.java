package cn.muye.user.api.service;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.api.service.AgentApplyService;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.shiro.service.ShiroService;
import cn.muye.user.domain.User;
import cn.muye.user.api.mapper.UserMapper;
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
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private ShiroService shiroService;

	@Autowired
	private AgentApplyService agentApplyService;

	public User getUserById(Long id) {
		return userMapper.getUserById(id);
	}

	public void saveAndBindRole(User user) {
		userMapper.save(user);
		shiroService.bindUserRole(user.getUserRoleId(), user.getId());
		//添加代理商申请记录
		AgentApply agentApply = new AgentApply();
		agentApply.setUserId(user.getId());
		agentApply.setUrl(user.getUrl());
		agentApply.setCreateTime(new Date());
		agentApply.setStatus(ApplyStatusType.SUBMIT.getValue());
		agentApplyService.save(agentApply);
	}

	/**
	 * 如果前台用户注册+申请代理商，就要添加代理商申请
	 * @param user
	 */
	public void saveAndApplyAgent(User user) {
		userMapper.save(user);
		shiroService.bindUserRole(user.getUserRoleId(), user.getId());
		//添加代理商申请记录
		AgentApply agentApply = new AgentApply();
		agentApply.setUserId(user.getId());
		agentApply.setUrl(user.getUrl());
		agentApply.setDescription(user.getDescription());
		agentApply.setCreateTime(new Date());
		agentApply.setStatus(ApplyStatusType.SUBMIT.getValue());
		agentApplyService.save(agentApply);
	}

	public void update(User user) {
		userMapper.update(user);
	}

	public User getUserByName(String userName) {
		return userMapper.getUserByName(userName);
	}

	public void deleteById(Long id) {
		userMapper.deleteById(id);
	}

	public User checkCustomerLogin(User user) {
		return userMapper.checkCustomerLogin(user);
	}
}
