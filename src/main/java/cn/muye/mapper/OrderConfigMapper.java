package cn.muye.mapper;

import cn.muye.model.OrderConfig;

import java.util.List;

/**
 * Created by Selim on 2017/4/19.
 */
public interface OrderConfigMapper {

    List<OrderConfig> listOrderConfigs();

    void saveOrderConfig(OrderConfig orderConfig);
}
