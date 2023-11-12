package com.hackathon.beside.common;

import com.hackathon.beside.common.exception.DuplicateAccount;
import com.hackathon.beside.common.exception.DuplicateNickname;
import com.hackathon.beside.common.response.FailResponse;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionResolver {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public FailResponse resolveNotFoundException(Exception e) {
        return new FailResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public FailResponse resolveException(Exception e) {
        return new FailResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DuplicateNickname.class})
    public FailResponse resolveDuplicateNickname(Exception e) {
        return new FailResponse("중복된 닉네임 입니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DuplicateAccount.class})
    public FailResponse resolveDuplicateAccount(Exception e) {
        return new FailResponse("중복된 아이디 입니다.");
    }
}
