package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class JwtValidationException extends JwtException {

    public JwtValidationException() {
        super(ErrorCode.JWT_VALIDATION_FAILED, ApiMessages.TOKEN_VALIDATION_FAILED);
    }
}