package com.fernirx.lms.common.constants;

public final class ErrorMessages {

    // ========== SYSTEM ERROR MESSAGES ==========
    public static final String METHOD_NOT_ALLOWED = "HTTP method not allowed";
    public static final String TOO_MANY_REQUESTS = "Too many requests";
    public static final String SERVICE_UNAVAILABLE = "Service unavailable";

    // ========== VALIDATION ERROR MESSAGES ==========
    public static final String REQUIRED_FIELD = "This field is required";
    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String VALUE_TOO_SHORT = "Value too short";
    public static final String INVALID_PHONE = "Invalid phone number";

    // ========== AUTHENTICATION ERROR MESSAGES ==========
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String ACCESS_DENIED = "Access denied";
    public static final String INVALID_TOKEN = "The provided JWT token is invalid";
    public static final String JWT_VALIDATION_FAILED = "An unexpected error occurred during JWT validation.";
    public static final String EXPIRED_TOKEN = "Token expired";
    public static final String INVALID_TOKEN_TYPE = "Invalid token type";
    public static final String INVALID_TOKEN_FORMAT = "Malformed JWT token";
    public static final String UNSUPPORTED_TOKEN = "Unsupported JWT token";

    // ========== RESOURCE NOT FOUND MESSAGES ==========
    public static final String USER_NOT_FOUND = "User not found";
    public static final String COURSE_NOT_FOUND = "Course not found";
    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String DEPARTMENT_NOT_FOUND = "Department not found";
    public static final String MAJOR_NOT_FOUND = "Major not found";
    public static final String TEACHER_NOT_FOUND = "Teacher not found";
    public static final String CLASS_NOT_FOUND = "Class not found";
    public static final String STUDENT_NOT_FOUND = "Student not found";
    public static final String OFFICE_NOT_FOUND = "Office not found";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String SCHOOL_YEAR_NOT_FOUND = "School year not found";
    public static final String SEMESTER_NOT_FOUND = "Semester not found";
    public static final String SUBJECT_NOT_FOUND = "Subject not found";
    public static final String OFFERING_NOT_FOUND = "Course offering not found";
    public static final String CLASSROOM_NOT_FOUND = "Classroom not found";
    public static final String ASSIGNMENT_NOT_FOUND = "Course assignment not found";
    public static final String REGISTRATION_NOT_FOUND = "Registration not found";
    public static final String TUITION_NOT_FOUND = "Tuition fee record not found";
    public static final String NOTICE_NOT_FOUND = "Notice not found";
    public static final String DOCUMENT_NOT_FOUND = "Course document not found";
    public static final String GRADE_NOT_FOUND = "Grade record not found";
    public static final String ASSIGNMENT_CHANGE_NOT_FOUND = "Assignment change record not found";
    public static final String DEPT_HEAD_NOT_FOUND = "Department head not found";
    public static final String TEACHER_MAJOR_NOT_FOUND = "Teacher major not found";
    public static final String EXAM_SCHEDULE_NOT_FOUND = "Exam schedule not found";
    public static final String OFFICE_HEAD_NOT_FOUND = "Office head not found";
    public static final String CONDUCT_SCORE_NOT_FOUND = "Conduct score not found";
    public static final String STUDENT_CREDITS_NOT_FOUND = "Student credits not found";
    public static final String PREREQUISITE_NOT_FOUND = "Course prerequisite not found";

    private ErrorMessages() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
