package cn.muye.cooperation.mapper;

import cn.muye.cooperation.domain.IsvApply;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
public interface IsvApplyMapper {


    IsvApply getById(Long id);

    List<IsvApply> list(Integer page);

    void save(IsvApply isvApply);

    void update(IsvApply isvApply);
}
