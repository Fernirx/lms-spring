package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.utils.ApiFormatter;
import lombok.Getter;

@Getter
public class DuplicateEntryException extends LmsException{
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public DuplicateEntryException(ErrorCode errorCode, String resourceName, String fieldName, Object fieldValue)  {
        super(errorCode, ApiFormatter.duplicateEntry(resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
