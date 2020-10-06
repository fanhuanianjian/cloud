package cn.com.yuuuu.service;

/**
 * Redis 异步缓存
 *
 * @Author: bhl
 * @Date: 2020/9/30
 * @Description: com.faw.membercenter.service
 * @version: 1.0
 */

public interface AsyncRedisService {

    /**
     * Redis key相关的操作
     */
    void keys();

    /**
     * 通用对象桶的操作
     */
    void bucket();

    /**
     * bitset操作
     */
    void bitset();

    /**
     * 原子长整形和原子双精度浮点
     */
    void atomicLongAndAtomicDouble();

    /**
     * 分布式锁
     */
    long reentrantLock();
}
