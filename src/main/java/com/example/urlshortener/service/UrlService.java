package com.example.urlshortener.service;

import com.example.urlshortener.exception.NotFoundException;
import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    private static final String ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // 缩短：先存拿 id → 生成短码 → 更新
    public String shorten(String originalUrl) {
        if (originalUrl == null || !originalUrl.startsWith("http")) {   // ← 新增
            throw new IllegalArgumentException("URL 不合法，必须以 http 或 https 开头");  // ← 新增
        }
        Url url = new Url(originalUrl);
        url = urlRepository.save(url);               // ① 插入，数据库发 id
        String shortCode = encodeBase62(url.getId()); // ② 用 id 生成短码
        url.setShortCode(shortCode);                  // ③ 设置短码
        urlRepository.save(url);                      // ④ 更新
        return shortCode;
    }

    // 跳转：查原网址
    public String getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new NotFoundException("短码不存在: " + shortCode));
    }

    // Base62 编码
    private String encodeBase62(long number) {
        if (number == 0) {
            return String.valueOf(ALPHABET.charAt(0));
        }
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(ALPHABET.charAt((int) (number % BASE)));
            number = number / BASE;
        }
        return sb.reverse().toString();
    }
}