package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class UnsupportedTokenException extends TokenException {

    public UnsupportedTokenException() {
        super(ErrorCode.UNSUPPORTED_TOKEN, ApiMessages.TOKEN_UNSUPPORTED);
    }
}