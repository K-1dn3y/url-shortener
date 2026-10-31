package com.example.urlshortener.controller;

import com.example.urlshortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    // 构造器：只需要一个 UrlService（之前多写了一个参数，已修正）
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // 缩短：POST /shorten?url=长网址
    @PostMapping("/shorten")
    public String shorten(@RequestParam String url) {
        return urlService.shorten(url);
    }

    // 跳转：GET /{shortCode} → 返回 302 重定向
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)      // 302 状态码
                .location(URI.create(originalUrl))          // Location 头 = 长网址
                .build();                                   // 构建响应
    }
}
