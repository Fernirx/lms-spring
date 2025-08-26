package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class MalformedTokenException extends LmsException {

    public MalformedTokenException() {
        super(ErrorCode.INVALID_TOKEN_FORMAT);
    }
}