package com.fernirx.lms.infrastructure.handler;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.dtos.responses.ErrorDetail;
import com.fernirx.lms.common.dtos.responses.ErrorResponse;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.LmsException;
import com.fernirx.lms.common.utils.MessageFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LmsException.class)
    public ResponseEntity<ErrorResponse> handleBusinessExceptions(LmsException ex) {
        logException(ex);
        return buildErrorResponse(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logException(ex);
        List<ErrorDetail> details = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    ErrorCode errorCode = ErrorCode.fromValidationCode(Objects.requireNonNull(fieldError.getCode()));
                    return ErrorDetail.of(errorCode, MessageFormatter.formatValidationMessage(fieldError), fieldError.getField(), fieldError.getRejectedValue());
                }).toList();

        return buildErrorResponse(
                ErrorCode.VALIDATION_ERROR,
                ApiMessages.FIELDS_VALIDATION_FAILED,
                details
        );
    }

    private void logException(Exception ex) {
        log.error("[BusinessException] {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
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

    public ResponseEntity<ErrorResponse> buildErrorResponse(
            ErrorCode mainErrorCode,
            String mainMessage,
            List<ErrorDetail> details) {

        ErrorResponse errorResponse = ErrorResponse.of(
                mainErrorCode,
                mainMessage,
                details
        );

        return ResponseEntity
                .status(mainErrorCode.getHttpStatus())
                .body(errorResponse);
    }
}
