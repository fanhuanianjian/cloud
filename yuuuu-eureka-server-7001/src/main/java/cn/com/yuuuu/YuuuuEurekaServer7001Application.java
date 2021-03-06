package cn.com.yuuuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YuuuuEurekaServer7001Application {

    public static void main(String[] args) {
        SpringApplication.run(YuuuuEurekaServer7001Application.class, args);
    }

}
