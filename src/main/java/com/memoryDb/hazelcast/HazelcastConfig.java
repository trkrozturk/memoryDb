package com.memoryDb.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    @Bean
    public Config configuration() {

        Config config = new Config();
        config.setInstanceName("hazelcast-instance");
        config.getNetworkConfig().setPort(6789);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("192.168.42.168:6787").addMember("192.168.42.168:6788").addMember("192.168.42.168:6789").setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("test");
        mapConfig.setTimeToLiveSeconds(-1);
        config.addMapConfig(mapConfig);
        return config;
    }
}
