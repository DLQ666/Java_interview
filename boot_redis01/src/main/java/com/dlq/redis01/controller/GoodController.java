package com.dlq.redis01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/buy_goods")
    public String buy_Goods() {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        try {
            Boolean flag = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS); //setNX

            if (!flag) {
                return "抢锁失败";
            }

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
            while (true)
            {
                redisTemplate.watch(REDIS_LOCK); //监控-REDIS_LOCK，类似 乐观锁
                if (value.equalsIgnoreCase(redisTemplate.opsForValue().get(REDIS_LOCK))){
                    redisTemplate.setEnableTransactionSupport(true);
                    redisTemplate.multi();//开始事务
                    redisTemplate.delete(REDIS_LOCK);
                    List<Object> list = redisTemplate.exec();
                    if (list == null) {  //如果等于null，就是没有删掉，删除失败，再回去while循环那再重新执行删除
                        continue;
                    }
                }
                redisTemplate.unwatch();//如果删除成功，释放监控器，并且breank跳出当前循环
                break;
            }
        }
    }
}
