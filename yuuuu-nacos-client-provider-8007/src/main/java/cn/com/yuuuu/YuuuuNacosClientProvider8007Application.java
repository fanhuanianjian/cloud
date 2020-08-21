package cn.com.yuuuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YuuuuNacosClientProvider8007Application {

    public static void main(String[] args) {
        SpringApplication.run(YuuuuNacosClientProvider8007Application.class, args);
    }

}
