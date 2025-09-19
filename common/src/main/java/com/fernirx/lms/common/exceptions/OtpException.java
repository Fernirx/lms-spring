package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class OtpException extends RuntimeException {
    private final ErrorCode errorCode;

    public OtpException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public OtpException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
