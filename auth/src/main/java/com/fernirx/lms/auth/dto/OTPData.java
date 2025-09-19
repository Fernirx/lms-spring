package com.fernirx.lms.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class OTPData {
    private String otp;
    private Instant expireAt;
    private Integer maxAttempts;

    public void incrementAttempts() {
        this.maxAttempts++;
    }
}
