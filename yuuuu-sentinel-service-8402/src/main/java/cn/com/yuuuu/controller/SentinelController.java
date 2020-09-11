package cn.com.yuuuu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @Author: bhl
 * @Date: 2020/9/9
 * @Description: cn.com.yuuuu.controller
 * @version: 1.0
 */
@RestController
@RequestScope
public class SentinelController {

    @Value("${server.info}")
    private String info;

    @GetMapping("/A")
    public String getInfoByFirst(){

        return info;
    }

    @GetMapping("/B")
    public String getInfoBySecond(){

        return info;
    }

}
