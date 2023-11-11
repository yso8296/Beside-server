package com.hackathon.beside.common.enums;

public enum InterestEnum {

    ECONOMY(1,"economy"),

    LAW(2, "law"),
    INSURANCE(3, "insurance"),
    COMMON_SENSE(4, "common_sense");

    private final long id;
    private final String content;

    private InterestEnum(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public static long convertToId(String content) {
        return InterestEnum.valueOf(content).id;
    }
}
