package com.example.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类：整个项目的入口。
 *
 * @SpringBootApplication 是三个注解的合体：
 *   - @Configuration     标记这是一个配置类
 *   - @EnableAutoConfiguration  自动配置（Spring Boot 的核心魔法）
 *   - @ComponentScan     自动扫描本类所在包及子包里的组件
 *
 * 所以它必须放在「最外层包」，才能扫到下面所有 controller/service。
 */
@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {
        // 启动内嵌 Tomcat，运行整个 Spring 应用
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}
