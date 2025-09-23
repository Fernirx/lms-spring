package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTokenTypeException extends TokenException {
    private final String expectedType;
    private final String tokenType;

    public InvalidTokenTypeException(String expectedType, String tokenType) {
        super(ErrorCode.INVALID_TOKEN_TYPE, ApiMessages.INVALID_TOKEN_TYPE);
        this.expectedType = expectedType;
        this.tokenType = tokenType;
    }
}
