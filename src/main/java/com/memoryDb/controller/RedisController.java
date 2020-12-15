package com.memoryDb.controller;

import com.memoryDb.ResponseObject;
import com.memoryDb.constants.ResponseTypes;
import com.memoryDb.redis.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/setKey")
    public String getKey(@RequestBody KeyValue keyValue) {
        return redisClient.setKey(keyValue);
    }

    @PostMapping(value = "/addRedis", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addRedis(@RequestBody Redis redis) {
        return dsProcess.addRedisDs(redis);
    }
    @DeleteMapping(value = "/removeRedis/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeRedis(@PathVariable(value="id") String id) {
        return dsProcess.removeRedisDs(id);
    }

    @GetMapping(value = "/getAllRedis", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllRedis() {
        return redisClient.getAllRedis();
    }

    @PostMapping(value = "/getSet")
    public String getSet(@RequestBody HashEntity hashEntity) {
        return redisClient.getSet(hashEntity).getAsJsonArray().toString();
    }

    @GetMapping(value = "/getInfo/{id}")
    public String getInfo(@PathVariable(value="id") String id) {
        return redisClient.getInfo(id);
    }
}
