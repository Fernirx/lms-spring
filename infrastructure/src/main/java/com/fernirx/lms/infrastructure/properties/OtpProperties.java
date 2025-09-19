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
@ConfigurationProperties(prefix = "otp.cache")
public class OtpProperties {
    @Min(value = 1, message = "Maximum size must be at least 1")
    @Max(value = 100_000, message = "Maximum size cannot exceed 100,000")
    private int maximumSize;

    @NotNull(message = "Expire-after-write must be set")
    @DurationMax(minutes = 60, message = "Expire-after-write cannot exceed 60 minutes")
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration expireAfterWrite;

    @PositiveOrZero(message = "Initial capacity must be >= 0")
    @Max(value = 10_000, message = "Initial capacity cannot exceed 10,000")
    private int initialCapacity;
}
