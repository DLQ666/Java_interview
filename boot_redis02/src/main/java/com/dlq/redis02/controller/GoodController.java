package com.dlq.redis02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/buy_goods")
    public String buy_Goods() {
        synchronized (this) {
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
        }
    }

}
