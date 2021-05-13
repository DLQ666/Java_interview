package com.dlq.redis01.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-05-13 11:17
 */
public class RedisUtils {

    public static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig, "dlqk8s.top", 6379);
    }

    public static Jedis getJedis() throws Exception{
        if (null != jedisPool) {
            return jedisPool.getResource();
        }
        throw new Exception("Jedispool is not ok");
    }
}
