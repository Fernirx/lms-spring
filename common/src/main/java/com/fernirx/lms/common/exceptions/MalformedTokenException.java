package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class MalformedTokenException extends TokenException {

    public MalformedTokenException() {
        super(ErrorCode.MALFORMED_TOKEN, ApiMessages.TOKEN_MALFORMED);
    }
}