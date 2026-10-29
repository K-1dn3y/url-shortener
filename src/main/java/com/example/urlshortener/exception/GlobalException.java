package com.example.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // 处理"资源不存在"异常 → 返回 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public Map<String, Object> handleNotFound(NotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", 404);
        body.put("message", e.getMessage());
        return body;
    }

    // 处理"参数不合法"异常 → 返回 400
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleInvalidArgument(IllegalArgumentException e){
        Map<String,Object> body = new HashMap<>();
        body.put("code",400);
        body.put("message",e.getMessage());
        return body;
    }
}
