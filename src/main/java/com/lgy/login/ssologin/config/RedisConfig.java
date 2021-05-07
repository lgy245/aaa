package com.lgy.login.ssologin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
@PropertySource(value = {"classpath:redis/redis.properties"})
public class RedisConfig {
    @Value("${redis.node.maxTotal}")
    private Integer maxTotal;
    @Value("${redis.node.host}")
    private String host;
    @Value("${redis.node.port}")
    private Integer port;

    /**
     * 设置最大链接数量
     * @return
     */
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        return jedisPoolConfig;
    }
    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = jedisPoolConfig();
        return new JedisPool(jedisPoolConfig,host,port);

    }
}
