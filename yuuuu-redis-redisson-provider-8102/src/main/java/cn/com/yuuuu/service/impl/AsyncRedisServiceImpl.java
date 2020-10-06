package cn.com.yuuuu.service.impl;

import cn.com.yuuuu.pojo.Student;
import cn.com.yuuuu.service.AsyncRedisService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: bhl
 * @Date: 2020/9/30
 * @Description: com.faw.membercenter.service.impl
 * @version: 1.0
 */
@Service
@Slf4j
public class AsyncRedisServiceImpl implements AsyncRedisService {


    @Autowired
    private RedissonClient redisson;

    private static final String ANOTHER_BITSET = "anotherBitset";

    // github 官方文档地址
    // https://github.com/redisson/redisson/wiki/Redisson%E9%A1%B9%E7%9B%AE%E4%BB%8B%E7%BB%8D

    /**
     * Redis key相关的操作
     */
    @Override
    public void keys() {
        /** Redis key相关的操作*/
        RKeys keys = redisson.getKeys();

        /** 获取所有的key*/
        Iterable<String> allKeys = keys.getKeys();
        for (String key : allKeys) {
            log.info("key:{}", key);
        }

        /** 获取匹配的key*/
        Iterable<String> foundedKeys = keys.getKeysByPattern("key*");
        for (String key : foundedKeys) {
            log.info("key:{}", key);
        }

        /** 根据key删除键值*/
        long numOfDeletedKeys = keys.delete("obj1", "obj2", "obj3");
        log.info("numOfDeletedKeys:{}", numOfDeletedKeys);

        /** 根据匹配的key删除键值*/
        long deletedKeysAmount = keys.deleteByPattern("test?");
        log.info("deletedKeysAmount:{}", deletedKeysAmount);

        /** 获取一个随机已存在的键值(无键值的时候返回null)*/
        String randomKey = keys.randomKey();
        log.info("randomKey:{}", randomKey);

        /** key的数量*/
        long keysAmount = keys.count();
        log.info("keysAmount:{}", keysAmount);

    }

    /**
     * 通用对象桶的操作
     */
    @Override
    public void bucket() {

        RBucket<Student> bucket = redisson.getBucket("student");
        bucket.set(new Student(1, "张三", 233));
        Student student = bucket.get();
        log.info("bucket:{}", student);

        // 尝试保存value值
        boolean trySet = bucket.trySet(new Student(2, "李四", 20));
        log.info("trySet:{}", trySet);

        // 原子替换桶的新值为var2
        bucket.compareAndSet(new Student(1, "张三", 233), new Student(4, "大师兄", 20));
        log.info("compareAndSet:{}", bucket.get());

        // 获取原来的value 并设置新的value
        Student studentGetAndSet = bucket.getAndSet(new Student(5, "二师兄", 2333));
        log.info("studentGetAndSet:{}", studentGetAndSet);
        log.info("studentGetAndSet:{}", bucket.get());

        // 批量操作多个RBucket对象
        RBuckets buckets = redisson.getBuckets();
        Map<String, Student> loadedBuckets = buckets.get("myBucket1", "myBucket2", "myBucket3");

        loadedBuckets.get("");

        Map<String, Object> map = new HashMap<>();
        map.put("myBucket1", new Student());
        map.put("myBucket2", new Student());

        // 利用Redis的事务特性，同时保存所有的通用对象桶，如果任意一个通用对象桶已经存在则放弃保存其他所有数据。
        buckets.trySet(map);

        // 同时保存全部通用对象桶。
        buckets.set(map);

    }

    /**
     * bitset操作
     */
    @Override
    public void bitset() {
        // 获取一个简单的Bitset
        RBitSet simpleBitset = redisson.getBitSet("simpleBitset");

        simpleBitset.set(0, true);

        simpleBitset.set(1);
        simpleBitset.set(1812, true);
        simpleBitset.clear(1812);

        log.info("bitset-0:{}", simpleBitset.get(0));
        log.info("bitset-1:{}", simpleBitset.get(1));
        log.info("bitset-1812:{}", simpleBitset.get(1812));

        log.info("-----------------");

        //获取另一个Bitset
        RBitSet anotherBitset = redisson.getBitSet(ANOTHER_BITSET);
        anotherBitset.set(0);
        anotherBitset.set(1, false);
        anotherBitset.set(1812, true);

        // 与anotherBitset按位异或计算
        simpleBitset.xor(ANOTHER_BITSET);

        log.info("bitset-xor-0:{}", simpleBitset.get(0));
        log.info("bitset-xor-1:{}", simpleBitset.get(1));
        log.info("bitset-xor-1812:{}", simpleBitset.get(1812));

    }

    /**
     * 原子长整形和原子双精度浮点
     */
    @Override
    public void atomicLongAndAtomicDouble() {

        //原子长整形
        RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
        atomicLong.set(3);
        atomicLong.incrementAndGet();
        atomicLong.get();

        //原子双精度浮点
        RAtomicDouble atomicDouble = redisson.getAtomicDouble("myAtomicDouble");
        atomicDouble.set(2.81);
        atomicDouble.addAndGet(4.11);
        atomicDouble.get();

    }

    /**
     * 分布式锁
     */
    /**
     * 分布式锁
     */
    @Override
    public long reentrantLock(){
        /**
         * 可重入锁
         */
        RLock lock = redisson.getLock("reentrantLock");
        long startTime = System.currentTimeMillis();

        // 最常见的使用方法
        // lock.lock();
        // 加锁以后10秒钟自动解锁 无需调用unlock方法手动解锁
        lock.lock(10, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(5);
            log.info("Thread:{}",Thread.currentThread().getName());
        }catch (Exception e){
            log.error("reentrantLockL:",e);
        } finally {
            //解锁
            lock.unlock();

        }


        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        // InterruptedException 中断异常
        // boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        // if (res) {
        //     try {

        //     } finally {
        //         lock.unlock();
        //     }
        // }

        return System.currentTimeMillis()-startTime;

    }

    /**
     * 公平锁
     */
    public void fairLock(){

        RLock fairLock = redisson.getFairLock("fairLock");
        // 最常见的使用方法
        // fairLock.lock();

    }
    /**
     * 公平锁
     */
    public void multiLock(){

        /**
         * 联锁
         */

        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");

        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
        // 同时加锁：lock1 lock2 lock3
        // 所有的锁都上锁成功才算成功。
        lock.lock();

        lock.unlock();

    }
    /**
     * 读写锁
     */
    public void readWriteLock(){

        RReadWriteLock rwlock = redisson.getReadWriteLock("anyRWLock");
        // 最常见的使用方法
        // rwlock.readLock().lock();
        // 或
        rwlock.writeLock().lock();


        try {

        }finally {
            rwlock.writeLock().unlock();
        }

    }

}
