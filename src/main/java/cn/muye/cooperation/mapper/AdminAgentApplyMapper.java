package cn.muye.cooperation.mapper;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
public interface AdminAgentApplyMapper {

    AgentApply getById(Long id);

    List<AgentApplyDto> list(Integer page, @Param(value = "status") Integer status);

    void save(AgentApply agentApply);

    void update(AgentApply agentApply);

    /**
     * 关联了用户表的查询，用户后台列表展示
     * @param id
     * @return
     */
    AgentApplyDto getByIdWithUser(Long id);

    AgentApplyDto getDtoByUserId(Long userId);

    AgentApply getByUserId(Long userId);
}
