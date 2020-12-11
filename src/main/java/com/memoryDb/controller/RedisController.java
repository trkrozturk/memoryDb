package com.memoryDb.controller;

import com.memoryDb.KeyEntity;
import com.memoryDb.redis.DsProcess;
import com.memoryDb.redis.HashEntity;
import com.memoryDb.redis.Redis;
import com.memoryDb.redis.RedisClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisClient redisClient;
    private final DsProcess dsProcess;

    public RedisController(RedisClient redisClient, DsProcess dsProcess) {
        this.redisClient = redisClient;
        this.dsProcess = dsProcess;
    }

    @PostMapping(value = "/getKey")
    public String getKey(@RequestBody KeyEntity key) {
        return redisClient.getKey(key).getAsJsonArray().toString();
    }

    @PostMapping(value = "/addRedis", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addRedis(@RequestBody List<Redis> redisList) {
        return dsProcess.addRedisDs(redisList) ? "true" : "false";
    }

    @PostMapping(value = "/getSet")
    public String getSet(@RequestBody HashEntity hashEntity) {
        return redisClient.getSet(hashEntity).getAsJsonArray().toString();
    }

    @PostMapping(value = "/getInfo")
    public String getInfo(@RequestBody Redis redis) {
        return redisClient.getInfo(redis);
    }
}
