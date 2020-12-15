package com.memoryDb.redis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValue {
    private String key;
    private String value;
    private String ip;
}
