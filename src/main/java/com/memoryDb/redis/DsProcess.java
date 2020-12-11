package com.memoryDb.redis;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.memoryDb.Constants.REDIS_LIST;

@Service
public class DsProcess {
    public boolean addRedisDs(List<Redis> redisList) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("redis.txt");


            for (Redis redis : redisList) {
                fw.write(redis.toString());
                fw.write("\n");
            }
            fw.close();
            REDIS_LIST = redisList;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadRedisInfo() {
        try {
            File file = new File("redis.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
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
            return false;
        }
        return true;
    }
}
