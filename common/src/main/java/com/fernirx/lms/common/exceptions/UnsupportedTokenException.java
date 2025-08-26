package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class UnsupportedTokenException extends LmsException {

    public UnsupportedTokenException() {
        super(ErrorCode.UNSUPPORTED_TOKEN);
    }
}