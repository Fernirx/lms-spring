package com.fernirx.lms.infrastructure.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    @NotBlank(message = "JWT secret cannot be blank")
    private String secret;

    @Positive(message = "JWT expiration must be positive")
    private long expiration;

    @Positive(message = "JWT refresh expiration must be positive")
    private long refreshExpiration;

    @NotBlank(message = "JWT issuer cannot be blank")
    private String issuer;
}
