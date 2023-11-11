package com.hackathon.beside.news.summary.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final String EXCEPTION_MESSAGE = "요청하신 필기 노트를 찾을 수 없습니다.";

    public ResourceNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }
}
