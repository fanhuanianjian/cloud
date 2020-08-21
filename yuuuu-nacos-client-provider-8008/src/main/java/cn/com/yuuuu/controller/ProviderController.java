package cn.com.yuuuu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: bhl
 * @Date: 2020/8/20
 * @Description: cn.com.yuuuu.controller
 * @version: 1.0
 */
@Controller
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/get/{id}")
    @ResponseBody
    public String getProvider(@PathVariable String id){

        return "这是："+port+":"+id;
    }

}
