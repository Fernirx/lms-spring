package com.fernirx.lms.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpVerifyResponse {
    private String resetPasswordToken;
    private String tokenType;
}
