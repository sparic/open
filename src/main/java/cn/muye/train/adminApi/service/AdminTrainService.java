package cn.muye.train.adminApi.service;

import cn.muye.train.adminApi.mapper.AdminTrainMapper;
import cn.muye.train.domain.Train;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sparic on 2018/3/7.
 */
@Service
@Transactional
public class AdminTrainService {

    @Autowired
    private AdminTrainMapper trainMapper;

    public List<Train> list(String queryObj) {
        Train train = JSON.parseObject(queryObj, Train.class);
        Map map = Maps.newHashMap();
        if (train != null) {
            map.put("teacher", train.getTeacher());
            return trainMapper.list(map);
        } else {
            return trainMapper.list(null);
        }
    }

    public int save(Train train) {
        train.setCreateTime(new Date());
        return trainMapper.save(train);
    }

    public Train getById(Long id) {
        return trainMapper.getById(id);
    }

    public int update(Train trainDb) {
        return trainMapper.update(trainDb);
    }
}
