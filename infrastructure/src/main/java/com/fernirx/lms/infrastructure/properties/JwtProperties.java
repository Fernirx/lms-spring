package com.fernirx.lms.infrastructure.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Data
@Validated
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    @NotBlank(message = "JWT secret cannot be blank")
    private String secret;

    @NotNull(message = "JWT expiration must be set")
    @DurationMin(minutes = 1, message = "JWT access token must be at least 1 minute")
    @DurationMax(hours = 24, message = "JWT access token cannot exceed 24 hours")
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration expiration;

    @NotNull(message = "JWT refresh expiration must be set")
    @DurationMin(hours = 1, message = "JWT refresh token must be at least 1 hour")
    @DurationMax(days = 30, message = "JWT refresh token cannot exceed 30 days")
    @DurationUnit(ChronoUnit.DAYS)
    private Duration refreshExpiration;

    @NotNull(message = "JWT reset password expiration must be set")
    @DurationMin(minutes = 5, message = "JWT reset password token must be at least 5 minutes")
    @DurationMax(hours = 1, message = "JWT reset password token cannot exceed 24 hours")
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration resetPasswordExpiration;

    @NotBlank(message = "JWT issuer cannot be blank")
    private String issuer;
}
