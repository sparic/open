package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.model.OrderConfig;
import cn.muye.service.OrderConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Selim on 2017/4/19.
 */
@Controller
public class OrderConfigController {

    @Autowired
    private OrderConfigService orderConfigService;

    @RequestMapping(value = "saveOrderConfig")
    @ResponseBody
    public AjaxResult saveOrderConfig(OrderConfig orderConfig){
        try {
            orderConfigService.saveOrderConfig(orderConfig);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("系统内部出错");
        }
    }

    @RequestMapping(value = "hasKeyWords")
    @ResponseBody
    public AjaxResult hasKeyWords(@RequestParam("question")String question){
        try {
            boolean hasKeyWords = orderConfigService.hasKeyWords(question);
            return AjaxResult.success(hasKeyWords);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("系统内部出错");
        }
    }
}
