package com.fernirx.lms.infrastructure.handler;

import com.fernirx.lms.common.dtos.responses.ErrorDetail;
import com.fernirx.lms.common.dtos.responses.ErrorResponse;
import com.fernirx.lms.common.enums.ErrorCategory;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler({
            InvalidTokenException.class,
            JwtValidationException.class,
            ExpiredTokenException.class,
            InvalidTokenTypeException.class,
            MalformedTokenException.class,
            UnsupportedTokenException.class
    })
    public ResponseEntity<ErrorResponse> handleSecurityExceptions(LmsException ex) {
        logException(ex);
        return buildErrorResponse(ex.getErrorCode());
    }

    private void logException(Exception ex) {
        if (ex instanceof ExpiredTokenException) {
            log.warn("[SecurityException] {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        } else {
            log.error("[SecurityException] {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        }
    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(ErrorCode errorCode) {
        ErrorDetail detail = ErrorDetail.of(errorCode, errorCode.getMessage());
        ErrorCategory category = errorCode.getCategory();

        ErrorResponse errorResponse = ErrorResponse.of(
                category,
                category.getMessage(),
                detail
        );
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }
}
