package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;

public class InvalidTokenException extends JwtException {

    public InvalidTokenException() {
        super(ErrorCode.TOKEN_INVALID, ApiMessages.TOKEN_INVALID);
    }
}