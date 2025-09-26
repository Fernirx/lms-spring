package com.fernirx.lms.infrastructure.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Data
@Validated
@ConfigurationProperties(prefix = "application.cors")
public class CORSProperties {
    @NotEmpty(message = "CORS allowedOrigins must not be empty")
    private Set<@NotBlank(message = "CORS origin cannot be blank") String> allowedOrigins;

    @NotEmpty(message = "CORS allowedMethods must not be empty")
    private Set<@NotBlank(message = "CORS method cannot be blank") String> allowedMethods;

    @NotEmpty(message = "CORS allowedHeaders must not be empty")
    private Set<@NotBlank(message = "CORS header cannot be blank") String> allowedHeaders;

    @NotNull(message = "CORS allowCredentials must be set (true/false)")
    private Boolean allowCredentials;

    @NotNull(message = "CORS maxAge must be set")
    @DurationMin(seconds = 60, message = "CORS maxAge must be at least 60 seconds")
    @DurationMax(hours = 24, message = "CORS maxAge cannot exceed 24 hours")
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration maxAge;
}
