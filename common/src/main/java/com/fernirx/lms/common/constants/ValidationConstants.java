package com.fernirx.lms.common.constants;

public final class ValidationConstants {

    // ========== FIELD LENGTHS ==========
    public static final int USERNAME_MAX_LENGTH = 50;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final int EMAIL_MAX_LENGTH = 100;
    public static final int PHONE_MAX_LENGTH = 15;
    public static final int NAME_MAX_LENGTH = 100;
    public static final int POSITION_MAX_LENGTH = 100;
    public static final int TITLE_MAX_LENGTH = 200;
    public static final int ADDRESS_MAX_LENGTH = 100;
    public static final int LOCATION_MAX_LENGTH = 100;
    public static final int FILE_URL_MAX_LENGTH = 255;
    public static final int ACADEMIC_DEGREE_MAX_LENGTH = 100;

    // ========== CODE LENGTHS ==========

    // Student codes: UG + DD + YYY + SSSSS = 12 chars (VARCHAR(12))
    public static final int STUDENT_CODE_MAX_LENGTH = 12;

    // Institution codes: VARCHAR(6)
    public static final int INSTITUTION_CODE_MAX_LENGTH = 6;

    // Department codes: VARCHAR(6)
    public static final int DEPARTMENT_CODE_MAX_LENGTH = 6;

    // Major codes: VARCHAR(8)
    public static final int MAJOR_CODE_MAX_LENGTH = 8;

    // Teacher codes: VARCHAR(8)
    public static final int TEACHER_CODE_MAX_LENGTH = 8;

    // Employee codes: VARCHAR(8)
    public static final int EMPLOYEE_CODE_MAX_LENGTH = 8;

    // Office codes: VARCHAR(6)
    public static final int OFFICE_CODE_MAX_LENGTH = 6;

    // Class codes: VARCHAR(8)
    public static final int CLASS_CODE_MAX_LENGTH = 8;

    // Subject codes: VARCHAR(8)
    public static final int SUBJECT_CODE_MAX_LENGTH = 8;

    // Classroom codes: VARCHAR(8)
    public static final int CLASSROOM_CODE_MAX_LENGTH = 8;

    // Semester codes: VARCHAR(6)
    public static final int SEMESTER_CODE_MAX_LENGTH = 6;

    // School year codes: VARCHAR(9) - format 2024-2025
    public static final int SCHOOL_YEAR_CODE_MAX_LENGTH = 9;

    // Course offering group codes: VARCHAR(10)
    public static final int GROUP_CODE_MAX_LENGTH = 10;

    // ========== PATTERNS ==========

    // Contact patterns
    public static final String PHONE_PATTERN = "^\\+[1-9]\\d{1,14}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{8,}$";
    public static final String NAME_PATTERN = "^[a-zA-ZÀ-ỹ\\s]{1,100}$";

    // Student codes: UG + 2 digits + 3 digits + 5 digits
    public static final String STUDENT_CODE_PATTERN = "^UG[0-9]{2}[0-9]{3}[0-9]{5}$";

    // Institution codes: 2-6 uppercase alphanumeric
    public static final String INSTITUTION_CODE_PATTERN = "^[A-Z0-9]{2,6}$";

    // Department codes: 2-6 uppercase alphanumeric
    public static final String DEPARTMENT_CODE_PATTERN = "^[A-Z0-9]{2,6}$";

    // Major codes: 2-8 uppercase alphanumeric
    public static final String MAJOR_CODE_PATTERN = "^[A-Z0-9]{2,8}$";

    // Teacher codes: T + 6 digits (but stored in VARCHAR(8))
    public static final String TEACHER_CODE_PATTERN = "^T[0-9]{6}$";

    // Employee codes: E + 6 digits (but stored in VARCHAR(8))
    public static final String EMPLOYEE_CODE_PATTERN = "^E[0-9]{6}$";

    // Office codes: 2-6 uppercase alphanumeric
    public static final String OFFICE_CODE_PATTERN = "^[A-Z0-9]{2,6}$";

    // Class codes: 2-8 uppercase alphanumeric
    public static final String CLASS_CODE_PATTERN = "^[A-Z0-9]{2,8}$";

    // Subject codes: 2-8 uppercase alphanumeric
    public static final String SUBJECT_CODE_PATTERN = "^[A-Z0-9]{2,8}$";

    // Classroom codes: 2-8 uppercase alphanumeric
    public static final String CLASSROOM_CODE_PATTERN = "^[A-Z0-9]{2,8}$";

    // Semester codes: HK1, HK2, HKH format (up to 6 chars for flexibility)
    public static final String SEMESTER_CODE_PATTERN = "^HK[1-3H]$";

    // School year codes: YYYY-YYYY format
    public static final String SCHOOL_YEAR_CODE_PATTERN = "^[0-9]{4}-[0-9]{4}$";

    // Group codes: 01, 02, 03... (up to 10 chars)
    public static final String GROUP_CODE_PATTERN = "^[0-9]{2}$";

    // ========== NUMERIC CONSTRAINTS ==========

    // Academic constraints
    public static final int MIN_CREDITS = 1;
    public static final int MAX_CREDITS = 10;
    public static final int MIN_CAPACITY = 1;
    public static final int MAX_CAPACITY = 1000;

    // Score constraints (DECIMAL(5,2))
    public static final double MIN_SCORE = 0.0;
    public static final double MAX_SCORE = 10.0;
    public static final double MIN_WEIGHT = 0.0;
    public static final double MAX_WEIGHT = 100.0;

    // Department number constraints (for code generation)
    public static final int MIN_DEPARTMENT_NUMBER = 1;
    public static final int MAX_DEPARTMENT_NUMBER = 99;

    // Tuition fee constraints (DECIMAL(12,2))
    public static final double MIN_TUITION_AMOUNT = 0.0;
    public static final double MAX_TUITION_AMOUNT = 9999999999.99;

    private ValidationConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
