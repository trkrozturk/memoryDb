package com.memoryDb.redis;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.memoryDb.KeyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

import static com.memoryDb.Constants.REDIS_LIST;

@Service("redisClient")
public class RedisClient {

    private static List<JedisPool> jedisConnections = new ArrayList<>();

    private DsProcess dsProcess;

    public synchronized void initJedisConnections() {

        if (REDIS_LIST == null) {
            dsProcess.loadRedisInfo();
        }

        final List<Redis> serverList = REDIS_LIST;

        for (Redis redis : serverList) {
            jedisConnections.add(new JedisPool(RedisConfig.buildPoolConfig(), redis.getIp(), redis.getPort(), 500));
        }

    }

    public JsonArray getKey(KeyEntity key) {

        JsonArray response = new JsonArray();
        for (JedisPool jedisConnection : getJedisConnections()) {
            String redisHost = jedisConnection.getResource().getClient().getHost();
            String redisResponse = jedisConnection.getResource().info();
            response.add(makeJson(redisHost, key.getKey(), redisResponse));
        }
        return response;
    }

    public JsonArray getSet(HashEntity hashEntity) {

        JsonArray response = new JsonArray();
        for (JedisPool jedisConnection : getJedisConnections()) {
            String redisHost = jedisConnection.getResource().getClient().getHost();
            String redisPort = String.valueOf(jedisConnection.getResource().getClient().getPort());
            String redisResponse = jedisConnection.getResource().hget(hashEntity.getKey(), hashEntity.getField());
            response.add(makeJson(redisHost, redisPort, hashEntity.getKey(), hashEntity.getField(), redisResponse));
        }
        return response;
    }

    private JsonObject makeJson(String... value) {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < value.length; i++) {
            jsonObject.addProperty(i + "", value[i]);
        }
        return jsonObject;
    }

    public List<JedisPool> getJedisConnections() {

        if (jedisConnections == null || jedisConnections.isEmpty())
            initJedisConnections();

        return jedisConnections;
    }

    @Autowired
    public void setDsProcess(DsProcess dsProcess) {
        this.dsProcess = dsProcess;
    }

    public String getInfo(Redis redis) {
        for (JedisPool jedisConnection : getJedisConnections()) {
            if (jedisConnection.getResource().getClient().getHost().equals(redis.getIp()) && jedisConnection.getResource().getClient().getPort() == redis.getPort()) {
                return jedisConnection.getResource().info();
            }
        }
        return null;
    }
}
