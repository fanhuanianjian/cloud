package cn.com.yuuuu.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: bhl
 * @Date: 2020/8/20
 * @Description: cn.com.yuuuu.config
 * @version: 1.0
 */
@Component
public class ApplicationConfig {

    @Bean
    @LoadBalanced
    private RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
