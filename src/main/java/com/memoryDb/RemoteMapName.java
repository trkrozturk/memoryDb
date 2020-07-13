package com.memoryDb;


/**
 * @author auretmen
 * @since 26.04.2019 09:48
 */
public enum RemoteMapName {

    PORTED_NUMBER("portedNumber"),
    ROUTED_NUMBER("routedNumber"),
    IPCH_CHARACTERISTIC_FROM_DID("ipchCharacteristicFromDid"),
    IPCH_CHARACTERISTIC_FROM_CLI("ipchCharacteristicFromCli"),
    IPCH_CFO("ipchCfo"),
    SERVICE_PLATFORM("servicePlatform"),
    OPTIONS_MESSAGE("optionsMessage"),
    ABC_TYPE_UMTH_NUMBER("abcTypeUmthNumber");

    private String value;

    RemoteMapName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
