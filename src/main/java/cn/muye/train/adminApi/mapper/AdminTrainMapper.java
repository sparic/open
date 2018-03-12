package cn.muye.train.adminApi.mapper;

import cn.muye.train.domain.Train;

import java.util.List;
import java.util.Map;

/**
 * Created by sparic on 2018/3/7.
 */
public interface AdminTrainMapper {

    List<Train> list(Map map);

    int save(Train train);

    Train getById(Long id);

    int update(Train trainDb);
}
