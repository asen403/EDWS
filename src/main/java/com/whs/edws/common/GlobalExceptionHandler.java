package com.whs.edws.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ApiResponse<String> handlerException(Exception e){
        return ApiResponse.fail(e.getMessage());
    }
}
