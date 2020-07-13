package com.memoryDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisClient redisClient;

    public RedisController(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @PostMapping(value = "/getKey")
    public String getKey(@RequestBody KeyEntity key) {
        return redisClient.getKey(key).getAsJsonArray().toString();
    }

    @PostMapping(value = "/getSet")
    public String getSet(@RequestBody HashEntity hashEntity) {
        return redisClient.getSet(hashEntity).getAsJsonArray().toString();
    }
}
