package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class JwtValidationException extends LmsException {

    public JwtValidationException() {
        super(ErrorCode.JWT_VALIDATION_FAILED);
    }
}