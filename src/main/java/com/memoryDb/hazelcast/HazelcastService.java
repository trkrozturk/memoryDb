package com.memoryDb.hazelcast;

import com.hazelcast.map.IMap;

public interface HazelcastService {

    String getDataByKey(String key);

    String createData(String key, String value);

    IMap<String, String> getData();

    String update(String key, String value);

    String deleteData(String key);

}
