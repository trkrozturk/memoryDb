package com.memoryDb.redis;

import com.google.gson.Gson;
import com.memoryDb.ResponseObject;
import com.memoryDb.constants.ResponseTypes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.memoryDb.constants.RedisServerList.REDIS_LIST;

@Service
public class DsProcess {

    public String addRedisDs(Redis redis) {

        if (checkDuplicate(redis)) {
            return new ResponseObject("Exist Ip and port", ResponseTypes.BAD_REQUEST).toString();
        }
        redis.setId(UUID.randomUUID().toString());
        REDIS_LIST.add(redis);

        return writeFile();
    }

    private String writeFile() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("redis.txt");


            for (Redis redisElem : REDIS_LIST) {
                fw.write(redisElem.toFile());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            return new ResponseObject(e.getMessage(), ResponseTypes.INTERNAL_SERVER_ERROR).toString();
        }
        return new ResponseObject("New redis add successfully",ResponseTypes.OK).toString();
    }

    private boolean checkDuplicate(Redis redis) {
        for (Redis redis1 : REDIS_LIST) {
            if (redis1.getIp().equals(redis.getIp()) && redis1.getPort() == redis.getPort()) {
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void loadRedisInfo() {
        try {
            File file = new File("redis.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            List<Redis> redisList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                Gson g = new Gson();
                Redis redis = g.fromJson(line, Redis.class);
                redisList.add(redis);

            }
            REDIS_LIST = redisList;
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public String removeRedisDs(String id) {
        try {
            File file = new File("redis.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            List<Redis> redisList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                Gson g = new Gson();
                Redis redis = g.fromJson(line, Redis.class);
                if (redis.getId().equals(id)) {
                    System.out.println("redis ip deleted" + redis.getIp());
                } else
                    redisList.add(redis);

            }
            REDIS_LIST = redisList;
            refreshRedisFile();
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();

            return new ResponseObject(e.getMessage(),ResponseTypes.BAD_REQUEST).toString();
        }
        return new ResponseObject("Redis Removed",ResponseTypes.OK).toString();
    }

    public void refreshRedisFile() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("redis.txt");
            for (Redis redisElem : REDIS_LIST) {
                fw.write(redisElem.toFile());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException ignored) {
        }
    }
}
