package com.dlq.redis01.controller;

import com.dlq.redis01.config.RedisConfig;
import com.dlq.redis01.util.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-05-12 15:05
 */
@RestController
public class GoodController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${server.port}")
    private String serverPort;

    public static final String REDIS_LOCK = "dlqLock";

    @Autowired
    private Redisson redisson;

    @GetMapping("/buy_goods")
    public String buy_Goods() throws Exception {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        RLock redissonLock = redisson.getLock(REDIS_LOCK);
        redissonLock.lock();

        try {

            String result = redisTemplate.opsForValue().get("goods:001"); //get key ----看看库存的数量够不够
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                redisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                System.out.println("成功买到商品，库存还剩下" + realNumber + " 件" + "\t 服务端口 " + serverPort);

                return "成功买到商品，库存还剩下" + realNumber + " 件" + "\t 服务端口 " + serverPort;
            } else {
                System.out.println("商品已经售完/活动结束/调用超时 " + "\t 服务端口" + serverPort);
            }
            return "商品已经售完/活动结束/调用超时 " + "\t 服务端口" + serverPort;
        } finally {
            //还在持有锁，并且   是当前线程持有的锁   再解锁
            //if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()){
                redissonLock.unlock();
            //}
        }
    }
}
