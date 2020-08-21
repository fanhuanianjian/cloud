package cn.com.yuuuu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: bhl
 * @Date: 2020/8/20
 * @Description: cn.com.yuuuu.controller
 * @version: 1.0
 */
@Controller
@Slf4j
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final String URL = "http://YUUUU-PROVIDER";

    @GetMapping("/get/{id}")
    @ResponseBody
    public String getProvider(@PathVariable String id){

        return  restTemplate.getForObject(URL+"/get/"+id,String.class);
    }


    @GetMapping("/discovery")
    @ResponseBody
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }


}
