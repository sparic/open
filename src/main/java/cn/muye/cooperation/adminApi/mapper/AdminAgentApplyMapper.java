package cn.muye.cooperation.adminApi.mapper;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
public interface AdminAgentApplyMapper {

    AgentApply getById(Long id);

    List<AgentApplyDto> list(Integer page, @Param("map") Map map);

    void update(AgentApply agentApply);

    /**
     * 关联了用户表的查询，用户后台列表展示
     * @param id
     * @return
     */
    AgentApplyDto getByIdWithUser(Long id);

}
