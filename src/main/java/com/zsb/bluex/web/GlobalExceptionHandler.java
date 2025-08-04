package com.zsb.bluex.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public WebResult<?> handleException(Exception e) {
        log.error("GlobalExceptionHandler捕获到异常:", e);
        return WebResult.failure(e.getMessage());
    }
}
