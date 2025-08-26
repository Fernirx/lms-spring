package com.fernirx.lms.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fernirx.lms.common.constants.ErrorMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ========== SYSTEM ERRORS ==========
    METHOD_NOT_ALLOWED("ERR_METHOD_NOT_ALLOWED", ErrorMessages.METHOD_NOT_ALLOWED,
            ErrorCategory.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED, Priority.MEDIUM),
    TOO_MANY_REQUESTS("ERR_TOO_MANY_REQUESTS", ErrorMessages.TOO_MANY_REQUESTS,
            ErrorCategory.RATE_LIMITING, HttpStatus.TOO_MANY_REQUESTS, Priority.HIGH),
    SERVICE_UNAVAILABLE("ERR_SERVICE_UNAVAILABLE", ErrorMessages.SERVICE_UNAVAILABLE,
            ErrorCategory.SERVER, HttpStatus.INTERNAL_SERVER_ERROR, Priority.CRITICAL),

    // ========== VALIDATION ERRORS ==========
    REQUIRED_FIELD("ERR_REQUIRED", ErrorMessages.REQUIRED_FIELD,
            ErrorCategory.VALIDATION, HttpStatus.BAD_REQUEST, Priority.LOW),
    INVALID_EMAIL("ERR_INVALID_EMAIL", ErrorMessages.INVALID_EMAIL,
            ErrorCategory.VALIDATION, HttpStatus.BAD_REQUEST, Priority.LOW),
    VALUE_TOO_SHORT("ERR_TOO_SHORT", ErrorMessages.VALUE_TOO_SHORT,
            ErrorCategory.VALIDATION, HttpStatus.BAD_REQUEST, Priority.LOW),
    INVALID_PHONE("ERR_INVALID_PHONE", ErrorMessages.INVALID_PHONE,
            ErrorCategory.VALIDATION, HttpStatus.BAD_REQUEST, Priority.LOW),

    // ========== AUTHENTICATION ERRORS ==========
    INVALID_CREDENTIALS("AUTH_INVALID_CREDENTIALS", ErrorMessages.INVALID_CREDENTIALS,
            ErrorCategory.AUTHENTICATION, HttpStatus.UNAUTHORIZED, Priority.HIGH),
    ACCESS_DENIED("AUTH_ACCESS_DENIED", ErrorMessages.ACCESS_DENIED,
            ErrorCategory.AUTHORIZATION, HttpStatus.FORBIDDEN, Priority.MEDIUM),
    INVALID_TOKEN("AUTH_INVALID_TOKEN", ErrorMessages.INVALID_TOKEN,
            ErrorCategory.AUTHENTICATION, HttpStatus.UNAUTHORIZED, Priority.HIGH),
    JWT_VALIDATION_FAILED("AUTH_JWT_VALIDATION_FAILED", ErrorMessages.JWT_VALIDATION_FAILED,
            ErrorCategory.AUTHENTICATION, HttpStatus.UNAUTHORIZED, Priority.HIGH),
    EXPIRED_TOKEN("AUTH_EXPIRED_TOKEN", ErrorMessages.EXPIRED_TOKEN,
            ErrorCategory.AUTHENTICATION, HttpStatus.UNAUTHORIZED, Priority.MEDIUM),
    INVALID_TOKEN_TYPE("AUTH_INVALID_TOKEN_TYPE", ErrorMessages.INVALID_TOKEN_TYPE,
            ErrorCategory.AUTHENTICATION, HttpStatus.UNAUTHORIZED, Priority.HIGH),
    INVALID_TOKEN_FORMAT("AUTH_INVALID_TOKEN_FORMAT", ErrorMessages.INVALID_TOKEN_FORMAT,
            ErrorCategory.AUTHENTICATION, HttpStatus.BAD_REQUEST, Priority.HIGH),
    UNSUPPORTED_TOKEN("AUTH_UNSUPPORTED_TOKEN", ErrorMessages.UNSUPPORTED_TOKEN,
            ErrorCategory.AUTHENTICATION, HttpStatus.UNAUTHORIZED, Priority.HIGH),

    // ========== RESOURCE NOT FOUND ERRORS ==========
    USER_NOT_FOUND("USER_NOT_FOUND", ErrorMessages.USER_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    COURSE_NOT_FOUND("COURSE_NOT_FOUND", ErrorMessages.COURSE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", ErrorMessages.ROLE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    DEPARTMENT_NOT_FOUND("DEPARTMENT_NOT_FOUND", ErrorMessages.DEPARTMENT_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    MAJOR_NOT_FOUND("MAJOR_NOT_FOUND", ErrorMessages.MAJOR_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    TEACHER_NOT_FOUND("TEACHER_NOT_FOUND", ErrorMessages.TEACHER_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    CLASS_NOT_FOUND("CLASS_NOT_FOUND", ErrorMessages.CLASS_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", ErrorMessages.STUDENT_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    OFFICE_NOT_FOUND("OFFICE_NOT_FOUND", ErrorMessages.OFFICE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    EMPLOYEE_NOT_FOUND("EMPLOYEE_NOT_FOUND", ErrorMessages.EMPLOYEE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    SCHOOL_YEAR_NOT_FOUND("SCHOOL_YEAR_NOT_FOUND", ErrorMessages.SCHOOL_YEAR_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    SEMESTER_NOT_FOUND("SEMESTER_NOT_FOUND", ErrorMessages.SEMESTER_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    SUBJECT_NOT_FOUND("SUBJECT_NOT_FOUND", ErrorMessages.SUBJECT_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    OFFERING_NOT_FOUND("OFFERING_NOT_FOUND", ErrorMessages.OFFERING_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    CLASSROOM_NOT_FOUND("CLASSROOM_NOT_FOUND", ErrorMessages.CLASSROOM_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    ASSIGNMENT_NOT_FOUND("ASSIGNMENT_NOT_FOUND", ErrorMessages.ASSIGNMENT_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    REGISTRATION_NOT_FOUND("REGISTRATION_NOT_FOUND", ErrorMessages.REGISTRATION_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    TUITION_NOT_FOUND("TUITION_NOT_FOUND", ErrorMessages.TUITION_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    NOTICE_NOT_FOUND("NOTICE_NOT_FOUND", ErrorMessages.NOTICE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    DOCUMENT_NOT_FOUND("DOCUMENT_NOT_FOUND", ErrorMessages.DOCUMENT_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    GRADE_NOT_FOUND("GRADE_NOT_FOUND", ErrorMessages.GRADE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    ASSIGNMENT_CHANGE_NOT_FOUND("ASSIGNMENT_CHANGE_NOT_FOUND", ErrorMessages.ASSIGNMENT_CHANGE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    DEPT_HEAD_NOT_FOUND("DEPT_HEAD_NOT_FOUND", ErrorMessages.DEPT_HEAD_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    TEACHER_MAJOR_NOT_FOUND("TEACHER_MAJOR_NOT_FOUND", ErrorMessages.TEACHER_MAJOR_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    EXAM_SCHEDULE_NOT_FOUND("EXAM_SCHEDULE_NOT_FOUND", ErrorMessages.EXAM_SCHEDULE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    OFFICE_HEAD_NOT_FOUND("OFFICE_HEAD_NOT_FOUND", ErrorMessages.OFFICE_HEAD_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    CONDUCT_SCORE_NOT_FOUND("CONDUCT_SCORE_NOT_FOUND", ErrorMessages.CONDUCT_SCORE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    STUDENT_CREDITS_NOT_FOUND("STUDENT_CREDITS_NOT_FOUND", ErrorMessages.STUDENT_CREDITS_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW),
    PREREQUISITE_NOT_FOUND("PREREQUISITE_NOT_FOUND", ErrorMessages.PREREQUISITE_NOT_FOUND,
            ErrorCategory.NOT_FOUND, HttpStatus.NOT_FOUND, Priority.LOW);

    private final String code;
    private final String message;
    private final ErrorCategory category;
    private final HttpStatus httpStatus;
    private final Priority priority;

    public static Optional<ErrorCode> fromCode(String code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst();
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public boolean isCategory(ErrorCategory category) {
        return this.category == category;
    }

    public int getLevel() {
        return this.priority.getLevel();
    }
}

@Getter
@RequiredArgsConstructor
enum Priority {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    CRITICAL(4);

    private final int level;
}