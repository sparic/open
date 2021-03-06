package cn.muye.cooperation.api.service;

import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.api.mapper.AgentApplyMapper;
import cn.muye.user.api.service.UserService;
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

    public AgentApply getById(Long id) {
        return agentApplyMapper.getById(id);
    }

    public void save(AgentApply agentApply) {
        agentApplyMapper.save(agentApply);
    }

    public void update(AgentApply agentApply) {
        agentApplyMapper.update(agentApply);
    }

    public AgentApplyDto getDtoByUserId(Long userId) {
        return agentApplyMapper.getDtoByUserId(userId);
    }

    public AgentApply getByUserId(Long userId) {
        return agentApplyMapper.getByUserId(userId);
    }
}
