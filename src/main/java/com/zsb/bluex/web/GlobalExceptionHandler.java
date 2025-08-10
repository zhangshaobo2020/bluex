package com.zsb.bluex.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public WebResult<?> handleException(Throwable e) {
        log.error("捕获到Throwable:", e);
        return WebResult.failure(e.getMessage());
    }
}
