package cn.com.yuuuu.controller;

import cn.com.yuuuu.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bhl
 * @Date: 2020/9/14
 * @Description: cn.com.yuuuu.controller
 * @version: 1.0
 */
@RestController
public class RabbitmqProviderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/sendMsg/direct")
    public String sendMessageIns(@RequestParam String msg){

        rabbitTemplate.convertAndSend(RabbitmqConfig.ITEM_DIRECT_EXCHANGE,"item.direct",msg);

        return "发送成功";
    }


}
