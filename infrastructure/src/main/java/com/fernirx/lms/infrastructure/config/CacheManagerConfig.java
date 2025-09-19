package com.fernirx.lms.infrastructure.config;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CacheManagerConfig {
    private final Cache<@NonNull Object, Object> cacheConfig;

    @Bean
    public CacheManager otpCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.registerCustomCache("otps", cacheConfig);
        return cacheManager;
    }
}
