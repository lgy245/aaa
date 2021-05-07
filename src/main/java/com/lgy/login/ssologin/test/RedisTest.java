package com.lgy.login.ssologin.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
    @Autowired
    JedisPool jedisPool;
    Jedis jedis = jedisPool.getResource();
    public static void main(String[] args) {

    }
    public static void testRedis(){
      //jedis.set("1","2");
    }
}
