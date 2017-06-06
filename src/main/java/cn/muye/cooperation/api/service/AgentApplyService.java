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

    @Autowired
    private UserService userService;

    private static final int STATUS_SUBMIT = 0; //已提交
    private static final int STATUS_AUDITING = 1; //待审核
    private static final int STATUS_SUCCESS = 2; //成功
    private static final int STATUS_FAILED = 3; //失败

    public AgentApply getById(Long id) {
        return agentApplyMapper.getById(id);
    }

    public List<AgentApplyDto> list(Integer page, Integer status) {
        return agentApplyMapper.list(page, status);
    }

    public void save(AgentApply agentApply) {
        agentApplyMapper.save(agentApply);
    }

    public String update(AgentApply agentApply, String type) {
        agentApplyMapper.update(agentApply);
        return null;
    }

    public AgentApplyDto getByIdWithUser(Long id) {
        return agentApplyMapper.getByIdWithUser(id);
    }

    public AgentApplyDto getDtoByUserId(Long userId) {
        return agentApplyMapper.getDtoByUserId(userId);
    }

    public AgentApply getByUserId(Long userId) {
        return agentApplyMapper.getByUserId(userId);
    }
}
