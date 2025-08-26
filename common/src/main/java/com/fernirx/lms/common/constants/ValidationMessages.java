package com.fernirx.lms.common.constants;

public final class ValidationMessages {

    // ========== COMMON VALIDATION MESSAGES ==========
    public static final String REQUIRED_MESSAGE = "This field cannot be empty";
    public static final String INVALID_FORMAT_MESSAGE = "Invalid format";
    public static final String TOO_LONG_MESSAGE = "Length exceeds the allowed limit";

    // ========== CONTACT VALIDATION MESSAGES ==========
    public static final String EMAIL_MESSAGE = "Invalid email format";
    public static final String PHONE_MESSAGE = "Invalid Vietnamese phone number format";
    public static final String PASSWORD_MESSAGE = "Password must be at least 8 characters long, including uppercase letters, lowercase letters, and numbers";
    public static final String NAME_MESSAGE = "Name can only contain letters and spaces";

    // ========== CODE VALIDATION MESSAGES ==========
    public static final String STUDENT_CODE_MESSAGE = "Student code must follow the format UG + 2 faculty digits + 3 year digits + 5 sequence digits";
    public static final String INSTITUTION_CODE_MESSAGE = "Institution code must be 2-6 uppercase letters";
    public static final String DEPARTMENT_CODE_MESSAGE = "Department code must be 2-6 uppercase letters";
    public static final String MAJOR_CODE_MESSAGE = "Major code must be 2-8 uppercase letters";
    public static final String TEACHER_CODE_MESSAGE = "Teacher code must follow the format T + 6 digits";
    public static final String EMPLOYEE_CODE_MESSAGE = "Employee code must follow the format E + 6 digits";
    public static final String OFFICE_CODE_MESSAGE = "Office code must be 2-6 uppercase letters";
    public static final String CLASS_CODE_MESSAGE = "Class code must be 2-8 uppercase letters";
    public static final String SUBJECT_CODE_MESSAGE = "Subject code must be 2-8 uppercase letters";
    public static final String CLASSROOM_CODE_MESSAGE = "Classroom code must be 2-8 uppercase letters";
    public static final String SEMESTER_CODE_MESSAGE = "Semester code must be HK1, HK2, or HKH";
    public static final String SCHOOL_YEAR_CODE_MESSAGE = "School year code must follow the format YYYY-YYYY";
    public static final String GROUP_CODE_MESSAGE = "Group code must be 2 digits";

    private ValidationMessages() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
