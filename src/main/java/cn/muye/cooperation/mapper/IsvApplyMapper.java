package cn.muye.cooperation.mapper;

import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.dto.IsvApplyDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
public interface IsvApplyMapper {

    IsvApply getById(Long id);

    List<IsvApplyDto> list(Integer page, @Param(value = "status") Integer status);

    void save(IsvApply isvApply);

    void update(IsvApply isvApply);

    /**
     * 关联了用户表的查询，用户后台列表展示
     * @param id
     * @return
     */
    IsvApplyDto getByIdWithUser(Long id);

    IsvApplyDto getDtoByUserId(Long userId);

    IsvApply getByUserId(Long userId);
}
