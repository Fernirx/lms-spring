package com.fernirx.lms.infrastructure.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "application.frontend")
public class FrontendProperties {
    @NotBlank(message = "Frontend base-url cannot be blank")
    private String baseUrl;

    @NotBlank(message = "Frontend login-url cannot be blank")
    private String loginUrl;
}
