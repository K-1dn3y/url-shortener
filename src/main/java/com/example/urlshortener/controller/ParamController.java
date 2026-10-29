package com.example.urlshortener.controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController// 告诉 Spring：这个类是接口类，返回值直接当响应内容
@RequestMapping("/greet") // 告诉 Spring：这个类里所有接口，网址都以 /greet 开头
public class ParamController {
    //①路径变量
    @GetMapping("/{name}")
    public String byPath(@PathVariable String name){
        return "Hello"+name+"!";
    }
    //②查询参数
    @GetMapping
    public String byParam(@RequestParam String name){
        return "Hello"+ name +"!";
    }
    //③Json请求体
    @PostMapping
    public String byBody(@RequestBody Map<String,String>body){
        return "Hello"+body.get("name")+"!";
    }
}
