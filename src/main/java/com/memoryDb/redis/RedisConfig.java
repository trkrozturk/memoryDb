package com.memoryDb.redis;

import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    private static final int MAX_TOTAL = 200;
    private static final int MIN_IDLE = 10;
    private static final int MAX_IDLE = 200;
    private static final int MAX_WAIT_MILLIS = 6000;

    public static JedisPoolConfig buildPoolConfig() {

        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(MAX_TOTAL);
        poolConfig.setMinIdle(MIN_IDLE);
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setBlockWhenExhausted(false);
        poolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
        return poolConfig;
    }
}
