package com.fernirx.lms.infrastructure.handler;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.dtos.responses.ErrorDetail;
import com.fernirx.lms.common.dtos.responses.ErrorResponse;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.LmsException;
import com.fernirx.lms.common.utils.ApiFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        logException(ex);
        String field = ex.getName();
        String value = ex.getValue() != null ? ex.getValue().toString() : "null";
        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
        ErrorDetail errorDetail = ErrorDetail.of(
                ErrorCode.INVALID_FIELD_TYPE,
                ApiFormatter.fieldTypeMismatch(ex.getName(), expectedType),
                field,
                value
        );
        return buildErrorResponse(
                ErrorCode.VALIDATION_ERROR,
                ApiMessages.INVALID_INPUT,
                List.of(errorDetail)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logException(ex);
        ErrorDetail errorDetail = ErrorDetail.of(
                ErrorCode.MALFORMED_JSON,
                ApiMessages.MALFORMED_REQUEST_BODY,
                null,
                null
        );
        return buildErrorResponse(
                ErrorCode.VALIDATION_ERROR,
                ApiMessages.INVALID_INPUT,
                List.of(errorDetail)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logException(ex);
        List<ErrorDetail> details = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    ErrorCode errorCode = ErrorCode.fromValidationCode(Objects.requireNonNull(fieldError.getCode()));
                    return ErrorDetail.of(errorCode, ApiFormatter.formatValidationMessage(fieldError), fieldError.getField(), fieldError.getRejectedValue());
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
