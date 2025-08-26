package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;

public class ResourceNotFoundException extends LmsException {

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}