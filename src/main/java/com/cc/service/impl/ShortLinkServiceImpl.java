package com.cc.service.impl;

import com.cc.service.ShortLinkService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ShortLinkServiceImpl implements ShortLinkService {
    private final StringRedisTemplate redisTemplate;

    public ShortLinkServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String createShortLink(String originalLink) {
        String shortLink = generateShortLink();
        redisTemplate.opsForValue().set(shortLink, originalLink,7, TimeUnit.DAYS);
        return shortLink;
    }

    @Override
    public String getOriginalLink(String shortLink) {
        return redisTemplate.opsForValue().get(shortLink);
    }

    private String generateShortLink() {
        // 实现短链生成算法,生成一个随机字符串作为短链
        UUID uuid1 = UUID.randomUUID();
        return uuid1.toString().replace("-","").substring(0,6);
    }
}
