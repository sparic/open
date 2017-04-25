package cn.muye.service;

import cn.muye.mapper.OrderConfigMapper;
import cn.muye.model.OrderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Selim on 2017/4/19.
 */
@Service
@Transactional
public class OrderConfigService {

    @Autowired
    private OrderConfigMapper orderConfigMapper;

    public boolean hasKeyWords(String question){
        boolean bool = false;
        List<OrderConfig> orderConfigList = orderConfigMapper.listOrderConfigs();
        for (OrderConfig orderConfig : orderConfigList) {
            String[] keyWords = orderConfig.getKeyWords().split("，");
            for (String keyWord : keyWords) {
                if(question.equals(keyWord)){
                    bool = true;
                    break;
                }
            }
            if(bool){
                break;
            }
        }
        return bool;
    }

    public void saveOrderConfig(OrderConfig orderConfig){
        orderConfigMapper.saveOrderConfig(orderConfig);
    }
}
