package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.utils.ApiFormatter;
import lombok.Getter;

@Getter
public class InvalidCredentialsException extends SecurityException {
    private final String username;

    public InvalidCredentialsException(String username) {
        super(ErrorCode.INVALID_CREDENTIALS, ApiFormatter.invalidCredentials(username));
        this.username = username;
    }
}
