package cn.com.yuuuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author bhl
 */
@SpringBootApplication
@EnableEurekaClient
public class YuuuuEurekaClientConsumer9002Application {

    public static void main(String[] args) {
        SpringApplication.run(YuuuuEurekaClientConsumer9002Application.class, args);
    }

}
