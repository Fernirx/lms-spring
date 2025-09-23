package com.fernirx.lms.common.exceptions;

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
}
