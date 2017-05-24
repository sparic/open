package cn.muye.cooperation.service;

import cn.muye.core.enums.ApplyStatusType;
import cn.muye.core.Constants;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.mapper.AgentApplyMapper;
import cn.muye.user.domain.User;
import cn.muye.user.service.UserService;
import cn.muye.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
@Service
@Transactional
public class AgentApplyService {

    @Autowired
    private AgentApplyMapper agentApplyMapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private UserService userService;

    public AgentApply getById(Long id) {
        return agentApplyMapper.getById(id);
    }

    public List<AgentApply> list(Integer page) {
        return agentApplyMapper.list(page);
    }

    public void save(AgentApply agentApply) {
        agentApplyMapper.save(agentApply);
    }

    public void update(AgentApply agentApply) {
        agentApplyMapper.update(agentApply);
        //创建账户
        if (agentApply.getStatus().equals(ApplyStatusType.SUCCESS.getValue())) {
            User user = new User();
            user.setUserName(agentApply.getUserName());
            user.setPassword(agentApply.getPassword());
            user.setUserRoleId(Constants.ISV_ROLE_ID);
            userService.saveAndBindRole(user);
        }
        sendMail(agentApply);
    }

    private void sendMail(AgentApply agentApply) {
        String subject = null;
        String[] emailArr = new String[]{agentApply.getUserName()};
        String context = null;
        if (agentApply.getStatus().equals(ApplyStatusType.SUCCESS.getValue())) {
            subject = "代理商资格" + ApplyStatusType.SUCCESS.getName();
            String userName = agentApply.getUserName();
            String password = agentApply.getPassword();
            context = agentApply.getContact() + ",你好! \t 恭喜" + agentApply.getCompanyName() + "获得木爷代理商资格";
            context += "\n\r 用户名:" + userName + "\n\r 密码:" + password;
            emailArr = new String[]{agentApply.getUserName()};
        } else if (agentApply.getStatus().equals(ApplyStatusType.FAILED.getValue())) {
            subject = "代理商资格" + ApplyStatusType.FAILED.getName();
            context = agentApply.getContact() + ",你好! \t很抱歉" + agentApply.getCompanyName() + "未通过木爷代理商资格审核。 \t 原因:" + agentApply.getComment();
        }
        mailUtil.send(emailArr, subject, context);
    }
}
