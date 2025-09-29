package com.fernirx.lms.infrastructure.properties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
@ConfigurationProperties(prefix = "application.cache.otp")
public class OtpProperties {
    @Min(value = 1, message = "Maximum size must be at least 1")
    @Max(value = 100_000, message = "Maximum size cannot exceed 100,000")
    private int maximumSize;

    @NotNull(message = "Expire-after-write must be set")
    @DurationMax(minutes = 60, message = "Expire-after-write cannot exceed 60 minutes")
    private Duration expireAfterWrite;

    @NotNull(message = "Max attempts must be specified")
    @Min(value = 1, message = "Max attempts must be at least 1")
    @Max(value = 10, message = "Max attempts cannot exceed 10")
    private Integer maxAttempts;

    @NotNull(message = "Max resend must be specified")
    @Min(value = 1, message = "Max resend must be at least 1")
    @Max(value = 10, message = "Max resend cannot exceed 10")
    private Integer maxResend;

    @NotNull(message = "Resend cooldown must be set")
    @DurationMin(seconds = 1, message = "Resend cooldown must be at least 1 second")
    @DurationMax(seconds = 60, message = "Resend cooldown cannot exceed 60 seconds")
    private Duration resendCooldown;

    @PositiveOrZero(message = "Initial capacity must be >= 0")
    @Max(value = 10_000, message = "Initial capacity cannot exceed 10,000")
    private int initialCapacity;
}
