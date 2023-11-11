package com.hackathon.beside.common.response;

import lombok.Getter;

@Getter
public class FailResponse {

    private final String message;

    public FailResponse(String message) {
        this.message = message;
    }
}
