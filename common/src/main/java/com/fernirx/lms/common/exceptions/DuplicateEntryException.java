package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;

public class DuplicateEntryException extends LmsException{

    public DuplicateEntryException(ErrorCode errorCode)  { super(errorCode); }
}
