package com.memoryDb.redis;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.memoryDb.ResponseObject;
import com.memoryDb.constants.ResponseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.memoryDb.constants.RedisServerList.REDIS_LIST;

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

    public String getInfo(String id) {

        Optional<Redis> redis = REDIS_LIST.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (redis.isPresent()) {
            for (JedisPool jedisConnection : getJedisConnections()) {
                if (jedisConnection.getResource().getClient().getHost().equals(redis.get().getIp()) && jedisConnection.getResource().getClient().getPort() == redis.get().getPort()) {
                    return new ResponseObject("Info Result",jedisConnection.getResource().info(), ResponseTypes.OK).toString();
                }
            }
        }

        return new ResponseObject("No Redis found With Given id",ResponseTypes.NOT_FOUND).toString();
    }

    public String setKey(KeyValue keyValue) {
        for (JedisPool jedisConnection : getJedisConnections()) {
            if (jedisConnection.getResource().getClient().getHost().equals(keyValue.getIp())) {

                return jedisConnection.getResource().set(keyValue.getKey(), keyValue.getValue());
            }
        }
        return null;
    }

    public String getAllRedis() {
        JsonArray jsonArray = new JsonArray();
        JsonParser parser = new JsonParser();
        for (Redis redis : REDIS_LIST) {
            jsonArray.add(parser.parse(redis.toString()).getAsJsonObject());
        }
        return jsonArray.toString();
    }
}
