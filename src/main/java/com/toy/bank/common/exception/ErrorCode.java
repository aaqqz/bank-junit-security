package com.toy.bank.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SECURITY_001(HttpStatus.UNAUTHORIZED, "Unauthorized user"),
    SECURITY_002(HttpStatus.FORBIDDEN, "Access denied"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
