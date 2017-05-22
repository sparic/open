package cn.muye.cooperation.service;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.ApplyStatusType;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.mapper.AgentApplyMapper;
import cn.muye.util.MailUtil;
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
        sendMail(agentApply);
    }

    private void sendMail(AgentApply agentApply) {
        String subject = null;
        String[] emailArr = new String[]{agentApply.getEmail()};
        String context = null;
        if (agentApply.getStatus().equals(ApplyStatusType.SUCCESS.getValue())) {
            subject = "代理商资格" + ApplyStatusType.SUCCESS.getName();
            String userName = agentApply.getUserName();
            String password = agentApply.getPassword();
            context = agentApply.getContact() + ",你好，恭喜" + agentApply.getCompanyName() + "获得木爷代理商资格";
            context += "<br> 用户名:" + userName + "密码:" + password;
            emailArr = new String[]{agentApply.getEmail()};
        } else if (agentApply.getStatus().equals(ApplyStatusType.FAILED.getValue())){
            subject = "代理商资格" + ApplyStatusType.FAILED.getName();
            context = agentApply.getContact() + ",你好，很抱歉" + agentApply.getCompanyName() + "未通过木爷代理商资格审核，原因:" + agentApply.getComment();
        }
        mailUtil.send(emailArr, subject, context);
    }
}
