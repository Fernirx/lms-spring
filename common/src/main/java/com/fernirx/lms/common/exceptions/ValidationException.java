package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ValidationException extends LmsException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
