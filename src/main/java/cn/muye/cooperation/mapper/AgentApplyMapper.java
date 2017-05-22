package cn.muye.cooperation.mapper;

import cn.muye.cooperation.domain.AgentApply;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
public interface AgentApplyMapper {

    AgentApply getById(Long id);

    List<AgentApply> list(Integer page);

    void save(AgentApply agentApply);

    void update(AgentApply agentApply);
}
