package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class LmsException extends RuntimeException {
    private final ErrorCode errorCode;

    public LmsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public LmsException(ErrorCode errorCode, String defaultMessage) {
        super(defaultMessage);
        this.errorCode = errorCode;
    }
}
