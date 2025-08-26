package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ExpiredTokenException extends LmsException {

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}