package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.utils.ApiFormatter;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends LmsException {
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(ErrorCode errorCode,  String resourceName, String fieldName, Object fieldValue) {
        super(errorCode, ApiFormatter.resourceNotFound(resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(ErrorCode errorCode, String resourceName) {
        super(errorCode, ApiFormatter.resourceNotFound(resourceName));
        this.resourceName = resourceName;
        this.fieldName = null;
        this.fieldValue = null;
    }
}