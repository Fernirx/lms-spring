package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

public class OtpException extends LmsException {

    public OtpException(ErrorCode errorCode) {
        super(errorCode);
    }

    public OtpException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
