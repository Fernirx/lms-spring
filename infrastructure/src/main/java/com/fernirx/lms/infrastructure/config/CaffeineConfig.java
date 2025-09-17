package com.fernirx.lms.infrastructure.config;

import com.fernirx.lms.infrastructure.properties.OtpProperties;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CaffeineConfig {
    private final OtpProperties otpProperties;

    @Bean
    public Cache<@NonNull String, @NonNull String> otpCache() {
        return Caffeine.newBuilder()
                .maximumSize(otpProperties.getMaximumSize())
                .expireAfterWrite(otpProperties.getExpireAfterWrite())
                .expireAfterAccess(otpProperties.getExpireAfterAccess())
                .initialCapacity(otpProperties.getInitialCapacity())
                .build();
    }
}
