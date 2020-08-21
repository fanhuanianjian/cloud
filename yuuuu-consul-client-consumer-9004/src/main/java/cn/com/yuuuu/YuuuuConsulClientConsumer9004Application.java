package cn.com.yuuuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YuuuuConsulClientConsumer9004Application {

    public static void main(String[] args) {
        SpringApplication.run(YuuuuConsulClientConsumer9004Application.class, args);
    }

}
