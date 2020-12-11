package com.memoryDb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemoryDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryDbApplication.class, args);
//        ClientConfig clientConfig = new ClientConfig();
//        ClientNetworkConfig clientNetworkConfig = new ClientNetworkConfig();
//        clientNetworkConfig.setAddresses(Arrays.asList("192.168.42.168:6785","192.168.42.168:6786","192.168.42.168:6787","192.168.42.168:6788","192.168.42.168:6799"));
//        clientConfig.setNetworkConfig(clientNetworkConfig);
//        Config config = new Config();
//        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("192.168.42.168:6787").addMember("192.168.42.168:6788").addMember("192.168.42.168:6789").setEnabled(true);
//        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
//        config.getNetworkConfig().setPort(6788);
//        HazelcastInstance client2 = Hazelcast.newHazelcastInstance(config);
//        config.getNetworkConfig().setPort(6787);
//        HazelcastInstance client3 = Hazelcast.newHazelcastInstance(config);
//        Config config2 = new Config();
//        config2.getNetworkConfig().setPort(6790);
//        config2.getNetworkConfig().getJoin().getTcpIpConfig().addMember("192.168.42.168:6787").addMember("192.168.42.168:6788").addMember("192.168.42.168:6789").setEnabled(true);
//        config2.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
//        config2.setInstanceName("turker");
//        HazelcastInstance client4 = Hazelcast.newHazelcastInstance(config2);
//        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
//        Map<Long, String> map2 = client.getMap("elements");
//        map2.put(1L,"asdasdasd");
//        for (Map.Entry<Long, String> entry : map2.entrySet()) {
//            System.out.println(String.format("Key: %d, Value: %s", entry.getKey(), entry.getValue()));
//        }
    }

}
