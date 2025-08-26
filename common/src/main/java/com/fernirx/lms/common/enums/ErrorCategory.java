package com.fernirx.lms.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCategory {
    AUTHENTICATION("AUTH", "Authentication related errors"),
    AUTHORIZATION("AUTHZ", "Authorization related errors"),
    NOT_FOUND("NOT_FOUND", "Resource not found errors"),
    CONFLICT("CONFLICT", "Resource conflict errors"),
    VALIDATION("VALIDATION", "Input validation errors"),
    RATE_LIMITING("RATE_LIMIT", "Rate limiting errors"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "HTTP method not allowed errors"),
    SERVER("SERVER", "Internal server errors");

    private final String code;
    private final String message;

    @JsonValue
    public String getCode() {
        return code;
    }
}
