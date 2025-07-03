package com.toy.bank.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // security
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "User is unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Access is forbidden"),

    // user
    ALREADY_EXISTS_USER(HttpStatus.BAD_REQUEST, "User already exists"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
