package cn.muye.user.service;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.ApplyStatusType;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.user.domain.User;
import cn.muye.user.mapper.UserMapper;
import cn.muye.util.MailUtil;
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

	public User getUserById(Long id) {
		return userMapper.getUserById(id);
	}

	public void saveAndSendMail(User user) {
		userMapper.save(user);
		sendMail(user);
	}

	private void sendMail(User user) {
		String subject = "您的账号已创建";
		String[] emailArr = new String[]{user.getEmailAddress()};
		String context = "用户名: " + user.getUserName() + "密码:" + user.getPassword();
		mailUtil.send(emailArr, subject, context);
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

	public User getUserInfoByUserPhone(String userPhone) {
		return userMapper.getUserInfoByUserPhone(userPhone);
	}

	public void deleteById(Long id) {
		userMapper.deleteById(id);
	}

}
