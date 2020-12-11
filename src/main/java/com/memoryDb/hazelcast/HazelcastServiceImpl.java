package com.memoryDb.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class HazelcastServiceImpl implements HazelcastService {
    private final HazelcastInstance hazelcastInstance;

    @Autowired
    HazelcastServiceImpl(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public String createData(String key, String value) {
        IMap<String, String> map = hazelcastInstance.getMap("my-map");
        map.put(key, value);
        return "Data is stored.";
    }

    @Override
    public String getDataByKey(String key) {
        IMap<String, String> map = hazelcastInstance.getMap("my-map");
        return map.get(key);
    }

    @Override
    public IMap<String, String> getData() {
        return hazelcastInstance.getMap("my-map");
    }

    @Override
    public String update(String key, String value) {
        IMap<String, String> map = hazelcastInstance.getMap("my-map");
        map.set(key, value);
        return "Data is stored.";
    }

    @Override
    public String deleteData(String key) {
        IMap<String, String> map = hazelcastInstance.getMap("my-map");
        return map.remove(key);
    }
}