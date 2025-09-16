package com.fernirx.lms.common.constants;

/**
 * Constants class containing standardized API response messages for various operations and error scenarios.
 * <p>
 * This class provides reusable message templates that support string formatting to create 
 * consistent API responses across the application. All messages use placeholder formatting 
 * compatible with {@link String#format(String, Object...)}.
 *
 * @author fernirx
 */
public final class ApiMessages {

    // ========== AUTHENTICATION & AUTHORIZATION MESSAGES ==========

    /**
     * Authentication success message template for login.
     * Expects 1 parameter: username.
     * Example: "User 'john@example.com' logged in successfully"
     */
    public static final String LOGIN_SUCCESS = "User '%s' logged in successfully";

    // ========== AUTHENTICATION & AUTHORIZATION ERROR MESSAGES ==========

    /**
     * Authentication failure message template for invalid credentials.
     * Expects 1 parameter: username.
     * Example: "Invalid credentials provided for user 'john@example.com'"
     */
    public static final String INVALID_CREDENTIALS = "Invalid credentials provided for user '%s'";

    /**
     * Authentication failure message template for account disabled.
     * Expects 1 parameter: username.
     * Example: "Account 'john@example.com' is disabled"
     */
    public static final String ACCOUNT_DISABLED = "Account '%s' is disabled";

    /**
     * Success message for refresh token operations.
     */
    public static final String REFRESH_TOKEN_SUCCESS = "Token refreshed successfully";

    // ========== RESOURCE OPERATIONS SUCCESS ==========

    /**
     * Success message template for resource creation operations.
     * Expects 1 parameter: resource name.
     * Example: "User created successfully"
     */
    public static final String RESOURCE_CREATED = "%s created successfully";

    /**
     * Success message template for resource update operations.
     * Expects 1 parameter: resource name.
     * Example: "User updated successfully"
     */
    public static final String RESOURCE_UPDATED = "%s updated successfully";

    /**
     * Success message template for resource deletion operations.
     * Expects 1 parameter: resource name.
     * Example: "User deleted successfully"
     */
    public static final String RESOURCE_DELETED = "%s deleted successfully";

    /**
     * Success message template for single resource retrieval operations.
     * Expects 1 parameter: resource name.
     * Example: "User retrieved successfully"
     */
    public static final String RESOURCE_RETRIEVED = "%s retrieved successfully";

    /**
     * Success message template for multiple resources retrieval operations.
     * Expects 1 parameter: resource name (plural).
     * Example: "Users retrieved successfully"
     */
    public static final String RESOURCES_RETRIEVED = "%s retrieved successfully";

    /**
     * Success message template for resource save operations.
     * Expects 1 parameter: resource name.
     * Example: "User saved successfully"
     */
    public static final String RESOURCE_SAVED = "%s saved successfully";

    /**
     * Success message template for resource restoration operations.
     * Expects 1 parameter: resource name.
     * Example: "User restored successfully"
     */
    public static final String RESOURCE_RESTORED = "%s restored successfully";

    /**
     * Success message template for resource activation operations.
     * Expects 1 parameter: resource name.
     * Example: "User activated successfully"
     */
    public static final String RESOURCE_ACTIVATED = "%s activated successfully";

    /**
     * Success message template for resource deactivation operations.
     * Expects 1 parameter: resource name.
     * Example: "User deactivated successfully"
     */
    public static final String RESOURCE_DEACTIVATED = "%s deactivated successfully";

    /**
     * Success message template for resource archiving operations.
     * Expects 1 parameter: resource name.
     * Example: "User archived successfully"
     */
    public static final String RESOURCE_ARCHIVED = "%s archived successfully";

    /**
     * Success message template for resource publishing operations.
     * Expects 1 parameter: resource name.
     * Example: "Article published successfully"
     */
    public static final String RESOURCE_PUBLISHED = "%s published successfully";

    /**
     * Success message template for resource unpublishing operations.
     * Expects 1 parameter: resource name.
     * Example: "Article unpublished successfully"
     */
    public static final String RESOURCE_UNPUBLISHED = "%s unpublished successfully";

    // ========== RESOURCE MESSAGE ==========

    /**
     * Error message template when a resource is not found.
     * Expects 1 parameter: resource name.
     * Example: "User not found"
     */
    public static final String RESOURCE_NOT_FOUND = "%s not found";

    /**
     * Error message template when a resource is not found with a specific field value.
     * Expects 3 parameters: resource name, field name, field value.
     * Example: "User not found with email: john@example.com"
     */
    public static final String RESOURCE_NOT_FOUND_WITH_FIELD = "%s not found with %s: %s";

    /**
     * Error message template when a resource is not found by ID.
     * Expects 2 parameters: resource name, ID value.
     * Example: "User with ID 123 not found"
     */
    public static final String RESOURCE_NOT_FOUND_BY_ID = "%s with ID %s not found";

    /**
     * Error message template when a resource already exists.
     * Expects 1 parameter: resource name.
     * Example: "User already exists"
     */
    public static final String RESOURCE_ALREADY_EXISTS = "%s already exists";

    /**
     * Error message template when a resource already exists with a specific field value.
     * Expects 3 parameters: resource name, field name, field value.
     * Example: "User with email 'john@example.com' already exists"
     */
    public static final String RESOURCE_ALREADY_EXISTS_WITH_FIELD = "%s with %s '%s' already exists";

    /**
     * Error message template when a resource cannot be deleted because it's in use.
     * Expects 1 parameter: resource name.
     * Example: "Category is currently in use and cannot be deleted"
     */
    public static final String RESOURCE_IN_USE = "%s is currently in use and cannot be deleted";

    // ========== VALIDATION MESSAGE ==========

    /**
     * Generic validation error message for multiple field validation failures.
     */
    public static final String FIELDS_VALIDATION_FAILED = "Validation failed for one or more fields";

    /**
     * Generic message for invalid input, e.g., type mismatch or format errors.
     */
    public static final String INVALID_INPUT = "Invalid input parameters provided";

    /**
     * Generic message for malformed or unreadable request body.
     */
    public static final String MALFORMED_REQUEST_BODY = "Request body is malformed or unreadable";

    /**
     * Validation error message template for required fields.
     * Expects 1 parameter: field name.
     * Example: "Email is required"
     */
    public static final String FIELD_REQUIRED = "%s is required";

    /**
     * Validation error message template for invalid field values.
     * Expects 1 parameter: field name.
     * Example: "Email is invalid"
     */
    public static final String FIELD_INVALID = "%s is invalid";

    /**
     * Validation error message template for fields that are too short.
     * Expects 2 parameters: field name, minimum length.
     * Example: "Password must be at least 8 characters"
     */
    public static final String FIELD_TOO_SHORT = "%s must be at least %d characters";

    /**
     * Validation error message template for fields that exceed maximum length.
     * Expects 2 parameters: field name, maximum length.
     * Example: "Username must not exceed 50 characters"
     */
    public static final String FIELD_TOO_LONG = "%s must not exceed %d characters";

    /**
     * Validation error message template for fields with values outside allowed range.
     * Expects 3 parameters: field name, minimum value, maximum value.
     * Example: "Age must be between 18 and 65"
     */
    public static final String FIELD_OUT_OF_RANGE = "%s must be between %s and %s";

    /**
     * Validation error message template for fields that must have unique values.
     * Expects 1 parameter: field name.
     * Example: "Email must be unique"
     */
    public static final String FIELD_MUST_BE_UNIQUE = "%s must be unique";

    /**
     * Validation error message template for fields that don't match required pattern.
     * Expects 2 parameters: field name, pattern.
     * Example: "Phone number must match pattern: \\d{3}-\\d{3}-\\d{4}"
     */
    public static final String FIELD_PATTERN_MISMATCH = "%s must match pattern: %s";

    /**
     * Validation error message template for fields with incorrect data type.
     * Expects 2 parameters: field name, expected type.
     * Example: "Age must be of type Integer"
     */
    public static final String FIELD_TYPE_MISMATCH = "%s must be of type %s";

    // ========== DATE/TIME MESSAGE ==========

    /**
     * Validation error message template for date fields that must be in the future.
     * Expects 1 parameter: field name.
     * Example: "Event date must be in the future"
     */
    public static final String DATE_MUST_BE_FUTURE = "%s must be in the future";

    /**
     * Validation error message template for date fields that must be in the past.
     * Expects 1 parameter: field name.
     * Example: "Birth date must be in the past"
     */
    public static final String DATE_MUST_BE_PAST = "%s must be in the past";

    /**
     * Validation error message template for date fields outside allowed range.
     * Expects 3 parameters: field name, start date, end date.
     * Example: "Appointment date must be between 2024-01-01 and 2024-12-31"
     */
    public static final String DATE_OUT_OF_RANGE = "%s must be between %s and %s";

    /**
     * Business rule error message template for conflicting time slots.
     * Expects 2 parameters: start time, end time.
     * Example: "Time slot conflict: 09:00 to 11:00"
     */
    public static final String TIME_SLOT_CONFLICT = "Time slot conflict: %s to %s";

    // ========== BUSINESS RULE MESSAGE ==========

    /**
     * Business rule error message template for invalid status transitions.
     * Expects 2 parameters: current status, target status.
     * Example: "Cannot transition from DRAFT to ARCHIVED"
     */
    public static final String STATUS_TRANSITION_INVALID = "Cannot transition from %s to %s";

    /**
     * Business rule error message template for operations not allowed in current state.
     * Expects 2 parameters: operation name, current state.
     * Example: "Operation 'DELETE' is not allowed in current state: PUBLISHED"
     */
    public static final String OPERATION_NOT_ALLOWED = "Operation '%s' is not allowed in current state: %s";

    /**
     * Business rule error message template for unmet prerequisites.
     * Expects 1 parameter: prerequisite description.
     * Example: "Prerequisite not met: User must verify email address"
     */
    public static final String PREREQUISITE_NOT_MET = "Prerequisite not met: %s";

    /**
     * Business rule error message template for exceeded limits.
     * Expects 3 parameters: limit type, current count, maximum allowed.
     * Example: "Upload limit exceeded: 5 of 3"
     */
    public static final String LIMIT_EXCEEDED = "%s limit exceeded: %d of %d";

    /**
     * Business rule error message template for insufficient quantity.
     * Expects 3 parameters: resource type, required quantity, available quantity.
     * Example: "Insufficient stock: required 10, available 5"
     */
    public static final String INSUFFICIENT_QUANTITY = "Insufficient %s: required %d, available %d";

    /**
     * Business rule error message template for duplicate entries.
     * Expects 3 parameters: resource name, field name, field value.
     * Example: "Order already exists with order number: ORD-001"
     */
    public static final String DUPLICATE_ENTRY = "%s already exists with %s: %s";

    // ========== AUTHENTICATION/AUTHORIZATION MESSAGE ==========

    /**
     * Authorization error message template for resource access denial.
     * Expects 2 parameters: resource type, resource identifier.
     * Example: "Access denied to Document: DOC-123"
     */
    public static final String ACCESS_DENIED_RESOURCE = "Access denied to %s: %s";

    /**
     * Authorization error message template for missing permissions.
     * Expects 1 parameter: permission name.
     * Example: "Permission 'READ_ADMIN_REPORTS' is required to access this resource"
     */
    public static final String PERMISSION_REQUIRED = "Permission '%s' is required to access this resource";

    /**
     * Authorization error message template for missing roles.
     * Expects 1 parameter: role name.
     * Example: "Role 'ADMIN' is required to perform this action"
     */
    public static final String ROLE_REQUIRED = "Role '%s' is required to perform this action";

    // ========== TOKEN MESSAGE ==========

    /**
     * Authentication error message for invalid JWT tokens.
     */
    public static final String TOKEN_INVALID = "The JWT token is invalid";

    /**
     * Authentication error message for invalid JWT token types.
     */
    public static final String INVALID_TOKEN_TYPE = "The JWT token type is invalid";

    /**
     * Authentication error message for expired JWT tokens.
     */
    public static final String TOKEN_EXPIRED = "The JWT token has expired";

    /**
     * Authentication error message for unsupported JWT token types.
     */
    public static final String TOKEN_UNSUPPORTED = "The JWT token type is not supported";

    /**
     * Authentication error message for malformed JWT tokens.
     */
    public static final String TOKEN_MALFORMED = "The JWT token is malformed";

    /**
     * Authentication error message for JWT token validation failures.
     */
    public static final String TOKEN_VALIDATION_FAILED = "Failed to validate the JWT token";

    // ========== FILE MESSAGE ==========

    /**
     * File upload error message template for files that exceed size limits.
     * Expects 2 parameters: actual file size, maximum allowed size.
     * Example: "File size 5.2MB exceeds maximum allowed size of 2MB"
     */
    public static final String FILE_TOO_LARGE = "File size %s exceeds maximum allowed size of %s";

    /**
     * File upload error message template for disallowed file types.
     * Expects 2 parameters: file type, allowed types list.
     * Example: "File type 'exe' is not allowed. Allowed types: jpg, png, pdf"
     */
    public static final String FILE_TYPE_NOT_ALLOWED = "File type '%s' is not allowed. Allowed types: %s";

    /**
     * File upload error message template for upload failures.
     * Expects 1 parameter: failure reason.
     * Example: "Failed to upload file: Disk quota exceeded"
     */
    public static final String FILE_UPLOAD_FAILED = "Failed to upload file: %s";

    // ========== BATCH OPERATION MESSAGE ==========

    /**
     * Batch operation result message template for partial success scenarios.
     * Expects 2 parameters: successful operations count, total operations count.
     * Example: "15 of 20 operations completed successfully"
     */
    public static final String BATCH_PARTIAL_SUCCESS = "%d of %d operations completed successfully";

    /**
     * Batch operation result message template when all operations fail.
     * Expects 1 parameter: total operations count.
     * Example: "All 10 operations failed"
     */
    public static final String BATCH_ALL_FAILED = "All %d operations failed";

    // ========== INTEGRATION MESSAGE ==========

    /**
     * Integration error message template for external service errors.
     * Expects 2 parameters: service name, error message.
     * Example: "External service 'PaymentGateway' returned error: Invalid API key"
     */
    public static final String EXTERNAL_SERVICE_ERROR = "External service '%s' returned error: %s";

    /**
     * Integration error message template for connection failures.
     * Expects 2 parameters: service name, failure reason.
     * Example: "Failed to connect to DatabaseServer: Connection timeout"
     */
    public static final String CONNECTION_FAILED = "Failed to connect to %s: %s";

    /**
     * Integration error message template for request timeouts.
     * Expects 2 parameters: service name, timeout duration in seconds.
     * Example: "Request to PaymentService timed out after 30 seconds"
     */
    public static final String TIMEOUT_ERROR = "Request to %s timed out after %d seconds";

    /**
     * Private constructor to prevent instantiation of this constants class.
     *
     * @throws UnsupportedOperationException always, as this class should not be instantiated
     */
    private ApiMessages() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}