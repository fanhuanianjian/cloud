package cn.com.yuuuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 毕洪磊
 */
@SpringBootApplication
@EnableDiscoveryClient
public class YuuuuZookeeperClientProvider8003Application {

    public static void main(String[] args) {
        SpringApplication.run(YuuuuZookeeperClientProvider8003Application.class, args);
    }

}
