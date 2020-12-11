package com.memoryDb.redis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Redis {
    private String ip;
    private int port;
    private String password;

    @Override
    public String toString() {
        return "{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                '}';
    }
}
