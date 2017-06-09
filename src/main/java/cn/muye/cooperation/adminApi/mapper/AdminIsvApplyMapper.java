package cn.muye.cooperation.adminApi.mapper;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.dto.IsvApplyDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
public interface AdminIsvApplyMapper {

    IsvApply getById(Long id);

    List<IsvApplyDto> list(Integer page, @Param(value = "map") Map map);

    void update(IsvApply isvApply);

    /**
     * 关联了用户表的查询，用户后台列表展示
     * @param id
     * @return
     */
    IsvApplyDto getByIdWithUser(Long id);

}
