package com.example.urlshortener.controller;
import com.example.urlshortener.model.Url;
import com.example.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;

@RestController

public class UrlController {
    private final UrlService urlService;
    public UrlController(UrlService urlService, UrlService urlService1){
        this.urlService = urlService1;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestParam String url){
        return urlService.shorten(url);
    }

    @GetMapping("/{shortCode}")
    public String redirect(@PathVariable String shortCode) {
        return "redirect:" + urlService.getOriginalUrl(shortCode);
    }
}