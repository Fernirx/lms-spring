package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;

public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException() {
        super(ErrorCode.TOKEN_EXPIRED, ApiMessages.TOKEN_EXPIRED);
    }
}