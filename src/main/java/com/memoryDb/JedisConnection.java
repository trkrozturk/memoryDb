package com.memoryDb;

import redis.clients.jedis.JedisPool;

/**
 * @author sdemirkaya
 * @since 2.01.2020 14:23
 */
public class JedisConnection {

    private final String redisIp;
    private final JedisPool jedisPool;

    public JedisConnection(String redisIp, JedisPool jedisPool) {
        this.redisIp = redisIp;
        this.jedisPool = jedisPool;
    }

    public String getRedisIp() {
        return redisIp;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

}
