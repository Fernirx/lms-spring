package com.fernirx.lms.common.constants;

public final class MessageConstants {

    // ========== SUCCESS MESSAGES ==========
    public static final String SUCCESS_CREATE = "Created successfully";
    public static final String SUCCESS_UPDATE = "Updated successfully";
    public static final String SUCCESS_DELETE = "Deleted successfully";
    public static final String SUCCESS_LOGIN = "Login successful";
    public static final String SUCCESS_LOGOUT = "Logout successful";
    public static final String SUCCESS_PASSWORD_RESET = "Password reset successful";
    public static final String SUCCESS_REGISTRATION = "Registration completed successfully";
    public static final String SUCCESS_UPLOAD = "File uploaded successfully";
    public static final String SUCCESS_IMPORT = "Data imported successfully";
    public static final String SUCCESS_EXPORT = "Data exported successfully";

    // ========== GENERAL ERROR MESSAGES ==========
    public static final String ERROR_UNAUTHORIZED = "Unauthorized access";
    public static final String ERROR_FORBIDDEN = "Access forbidden";
    public static final String ERROR_NOT_FOUND = "Resource not found";
    public static final String ERROR_BAD_REQUEST = "Invalid request";
    public static final String ERROR_INTERNAL_SERVER = "Internal server error";
    public static final String ERROR_CONFLICT = "Resource conflict";
    public static final String ERROR_VALIDATION_FAILED = "Validation failed";

    // ========== OPERATION MESSAGES ==========
    public static final String CONFIRM_DELETE = "Are you sure you want to delete this item?";
    public static final String CONFIRM_LOGOUT = "Are you sure you want to logout?";
    public static final String LOADING = "Loading...";
    public static final String PROCESSING = "Processing...";
    public static final String SAVING = "Saving...";
    public static final String NO_DATA_AVAILABLE = "No data available";
    public static final String PLEASE_SELECT = "Please select";
    public static final String CHOOSE_FILE = "Choose file";

    private MessageConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
