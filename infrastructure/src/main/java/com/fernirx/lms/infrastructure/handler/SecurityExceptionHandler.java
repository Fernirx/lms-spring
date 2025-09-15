package com.fernirx.lms.infrastructure.handler;

import com.fernirx.lms.common.dtos.responses.ErrorDetail;
import com.fernirx.lms.common.dtos.responses.ErrorResponse;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.*;
import com.fernirx.lms.common.exceptions.SecurityException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler({
            InvalidCredentialsException.class,
            AccountDisabledException.class
    })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(SecurityException ex) {
        log.warn("[SecurityException] {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return buildErrorResponse(ex);
    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(LmsException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(
                errorCode,
                ex.getMessage()
        );
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }
}
