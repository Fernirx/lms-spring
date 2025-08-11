package com.fernirx.lms.common.exceptions;

public class LmsException extends RuntimeException {
    public LmsException(String message) {
        super(message);
    }

    public LmsException(String message, Throwable cause) {
        super(message, cause);
    }
}
