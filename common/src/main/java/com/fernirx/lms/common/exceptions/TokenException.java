package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class TokenException extends AuthenticationException {
    private final ErrorCode errorCode;

    public TokenException(ErrorCode errorCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    public TokenException(ErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public static TokenException expired() {
        return new TokenException(ErrorCode.TOKEN_EXPIRED, ApiMessages.TOKEN_EXPIRED);
    }

    public static TokenException malformed() {
        return new TokenException(ErrorCode.MALFORMED_TOKEN, ApiMessages.TOKEN_MALFORMED);
    }

    public static TokenException unsupported() {
        return new TokenException(ErrorCode.UNSUPPORTED_TOKEN, ApiMessages.TOKEN_UNSUPPORTED);
    }

    public static TokenException invalid() {
        return new TokenException(ErrorCode.TOKEN_INVALID, ApiMessages.TOKEN_INVALID);
    }

    public static TokenException validationFailed() {
        return new TokenException(ErrorCode.JWT_VALIDATION_FAILED, ApiMessages.TOKEN_VALIDATION_FAILED);
    }

    public static TokenException invalidType() {
        return new TokenException(ErrorCode.INVALID_TOKEN_TYPE, ApiMessages.INVALID_TOKEN_TYPE);
    }
}
