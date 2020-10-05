package cn.com.yuuuu.controller;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bhl
 * @Date: 2020/9/14
 * @Description: cn.com.yuuuu.controller
 * @version: 1.0
 */
@RestController
public class RabbitmqConsumerController {

    /**
     * 监听某个队列的消息
     * @param message 接收到的消息
     */
    @RabbitListener(queues = "item_queue")
    public void myListener1(String message){
        System.out.println("消费者接收到的消息为1：" + message);
    }

    @RabbitListener(queues = "item_direct_queue")
    public void myListener2(String message){
        System.out.println("direct:消费者接收到的消息为：" + message);
    }

}
