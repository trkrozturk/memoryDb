package com.memoryDb.hazelcast;

public enum HazelcastType {
    REPLICATED_MAP("REPLICATED_MAP"),
    MAP("MAP"),
    RING_BUFFER("RING_BUFFER"),
    SET("SET");

    public final String label;

    private HazelcastType(String label) {
        this.label = label;
    }
}
