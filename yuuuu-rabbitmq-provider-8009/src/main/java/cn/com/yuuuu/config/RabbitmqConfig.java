package cn.com.yuuuu.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bhl
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 直连型交换机名称
     */
    public static final String ITEM_DIRECT_EXCHANGE = "item_direct_exchange";

    /**
     * 直连型交换机队列名称
     */
    public static final String ITEM_DIRECT_QUEUE = "item_direct_queue";



    /**
     * 声明直连型交换机
     */
    @Bean("itemDirectExchange")
    public Exchange directExchange(){
        return ExchangeBuilder.directExchange(ITEM_DIRECT_EXCHANGE).durable(true).build();
    }


    /**
     * 声明队列 (直连型)
     */
    @Bean("itemDirectQueue")
    public Queue itemDirectQueue(){
        return QueueBuilder.durable(ITEM_DIRECT_QUEUE).build();
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding itemDirectQueueExchange(@Qualifier("itemDirectQueue") Queue queue,
                                      @Qualifier("itemDirectExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.direct").noargs();
    }

}