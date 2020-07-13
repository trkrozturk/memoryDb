package com.memoryDb;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service("redisClient")
public class RedisClient {

    private static final Integer REDIS_PORT = 6379;
    private static List<JedisConnection> jedisConnections = new ArrayList<>();


    public synchronized void initJedisConnections() {

        final String serverList = "127.0.0.1";

        if (serverList == null) {
            throw new IllegalArgumentException("There is no redis server list in icom_parametre table!");
        }

        String[] jedisEndpoints = serverList.split(";");
        for (String jedisEndpoint : jedisEndpoints) {
            jedisConnections.add(new JedisConnection(new Jedis(jedisEndpoint, REDIS_PORT, 500)));
        }

    }

    public JsonArray getKey(KeyEntity key) {

        JsonArray response = new JsonArray();
        for (JedisConnection jedisConnection : getJedisConnections()) {
            String redisHost = jedisConnection.getJedis().getClient().getHost();
            String redisResponse = jedisConnection.getJedis().get(key.getKey());

            response.add(makeJson(redisHost, key.getKey(), redisResponse));
        }
        return response;
    }

    public JsonArray getSet(HashEntity hashEntity) {

        JsonArray response = new JsonArray();
        for (JedisConnection jedisConnection : getJedisConnections()) {
            String redisHost = jedisConnection.getJedis().getClient().getHost();
            String redisResponse = jedisConnection.getJedis().hget(hashEntity.getKey(), hashEntity.getField());
            response.add(makeJson(redisHost, hashEntity.getKey(), hashEntity.getField(), redisResponse));
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

    public List<JedisConnection> getJedisConnections() {

        if (jedisConnections == null || jedisConnections.isEmpty())
            initJedisConnections();

        return jedisConnections;
    }
}
