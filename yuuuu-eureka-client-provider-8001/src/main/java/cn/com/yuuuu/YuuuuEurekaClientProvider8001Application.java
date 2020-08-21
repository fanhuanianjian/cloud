package cn.com.yuuuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YuuuuEurekaClientProvider8001Application {

    public static void main(String[] args) {
        SpringApplication.run(YuuuuEurekaClientProvider8001Application.class, args);
    }

}
