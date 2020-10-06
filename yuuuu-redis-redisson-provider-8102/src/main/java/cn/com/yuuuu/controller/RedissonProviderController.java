package cn.com.yuuuu.controller;

import cn.com.yuuuu.service.AsyncRedisService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * @author bhl
 * @Date: 2020/10/5
 * @Description: cn.com.yuuuu.controller
 * @version: 1.0
 */
@RestController
@Slf4j
public class RedissonProviderController {


    @Autowired
    private RedissonClient redisson;

    @Autowired
    private AsyncRedisService asyncRedisService;

    @GetMapping("/keys")
    public String keys() {

        asyncRedisService.keys();

        return "ok";
    }

    @GetMapping("/bucket")
    public String bucket() {

        asyncRedisService.bucket();

        return "ok";
    }

    @GetMapping("/bitset")
    public String bitset() {

        asyncRedisService.bitset();

        return "ok";
    }


    @GetMapping("/atomic")
    public String atomic() {

        asyncRedisService.atomicLongAndAtomicDouble();

        return "ok";
    }

    @GetMapping("/reentrantLock")
    public String reentrantLock() {

        return "Time:"+asyncRedisService.reentrantLock();
    }



}
