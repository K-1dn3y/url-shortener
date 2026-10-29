package com.example.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第一个 Controller：理解「接口层」怎么接收请求、返回响应。
 *
 * @RestController 表示这是一个「返回 JSON/文本」的接口类，
 *   （区别于 @Controller，后者返回视图名，交给模板引擎渲染页面）
 */
@RestController
public class HelloController {

    /**
     * @GetMapping("/hello") 表示：浏览器访问 http://localhost:8080/hello 时，
     * 调用这个方法，并把返回值直接作为响应返回。
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
