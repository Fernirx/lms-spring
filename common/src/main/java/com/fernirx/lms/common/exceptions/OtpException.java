package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;

public class OtpException extends LmsException {

    public OtpException(ErrorCode errorCode) {
        super(errorCode);
    }

    public OtpException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static OtpException maxResendExceeded() {
        return new OtpException(ErrorCode.OTP_MAX_RESEND_EXCEEDED, ApiMessages.OTP_MAX_RESEND_EXCEEDED);
    }

    public static OtpException resendCooldownExceeded() {
        return new OtpException(ErrorCode.OTP_RESEND_COOLDOWN_EXCEEDED, ApiMessages.OTP_RESEND_COOLDOWN_EXCEEDED);
    }

    public static OtpException notFound() {
        return new OtpException(ErrorCode.OTP_NOT_FOUND, ApiMessages.OTP_NOT_FOUND);
    }

    public static OtpException expired() {
        return new OtpException(ErrorCode.OTP_EXPIRED, ApiMessages.OTP_EXPIRED);
    }

    public static OtpException maxAttemptsExceeded() {
        return new OtpException(ErrorCode.OTP_MAX_ATTEMPTS_EXCEED, ApiMessages.OTP_MAX_ATTEMPTS_EXCEED);
    }

    public static OtpException invalid() {
        return new OtpException(ErrorCode.OTP_INVALID, ApiMessages.OTP_INVALID);
    }
}
