package cn.muye.cooperation.service;

import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.core.Constants;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.mapper.AgentApplyMapper;
import cn.muye.user.domain.User;
import cn.muye.user.service.UserService;
import cn.muye.utils.MailUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

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

    public List<AgentApplyDto> list(Integer page, Integer status) {
        return agentApplyMapper.list(page, status);
    }

    public void save(AgentApply agentApply) {
        agentApplyMapper.save(agentApply);
    }

    public void update(AgentApply agentApply) {
        agentApplyMapper.update(agentApply);
        //更改用户角色
        if (agentApply.getStatus().equals(ApplyStatusType.SUCCESS.getValue())) {
            Long userId = agentApply.getUserId();
            User userDb = userService.getUserById(userId);
            userDb.setUserRoleId(Constants.AGENT_ROLE_ID);
            userService.updateAndBindRole(userDb);
        }
        AgentApplyDto agentApplyDto = agentApplyMapper.getByIdWithUser(agentApply.getId());
        sendMail(agentApplyDto);
    }

    private void sendMail(AgentApplyDto agentApplyDto) {
        String subject = null;
        String[] emailArr = new String[]{agentApplyDto.getEmail()};
        String context = null;
        if (Integer.valueOf(agentApplyDto.getStatus()).equals(ApplyStatusType.SUCCESS.getValue())) {
            subject = "代理商资格认证" + ApplyStatusType.SUCCESS.getName();
            context = agentApplyDto.getUserName() + ",你好! \t 恭喜贵公司获得木爷机器人代理商资格";
        } else if (Integer.valueOf(agentApplyDto.getStatus()).equals(ApplyStatusType.FAILED.getValue())) {
            subject = "代理商资格认证" + ApplyStatusType.FAILED.getName();
            context = agentApplyDto.getUserName()+ ",你好! \t很抱歉贵公司未通过木爷机器人代理商资格审核 \t 原因:" + agentApplyDto.getDescription();
        }
        mailUtil.send(emailArr, subject, context);
    }

    public AgentApplyDto getByIdWithUser(Long id) {
        return agentApplyMapper.getByIdWithUser(id);
    }

    public AgentApplyDto getDtoByUserId(Long userId) {
        return agentApplyMapper.getDtoByUserId(userId);
    }
}
