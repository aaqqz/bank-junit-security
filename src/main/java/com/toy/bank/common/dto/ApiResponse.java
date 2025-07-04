package com.toy.bank.common.dto;

import com.toy.bank.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private final HttpStatus statusCode;
    private final String message;
    private final String errorCode;
    private final T data;

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(HttpStatus.OK, "", null, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK, "", null, data);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getHttpStatus(), errorCode.getMessage(), errorCode.name(), null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode, T errorMap) {
        return new ApiResponse<>(errorCode.getHttpStatus(), errorCode.getMessage(), errorCode.name(), errorMap);
    }
}
