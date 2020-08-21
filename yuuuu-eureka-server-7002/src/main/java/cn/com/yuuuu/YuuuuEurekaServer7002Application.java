package cn.com.yuuuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YuuuuEurekaServer7002Application {

    public static void main(String[] args) {
        SpringApplication.run(YuuuuEurekaServer7002Application.class, args);
    }

}
