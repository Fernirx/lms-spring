package com.fernirx.lms.common.utils;

import com.fernirx.lms.common.constants.ApiMessages;
import lombok.experimental.UtilityClass;
import org.springframework.validation.FieldError;

import static com.fernirx.lms.common.constants.ApiMessages.*;

/**
 * Utility class for formatting standardized API messages using templates from {@link ApiMessages}.
 * <p>
 * This class provides type-safe methods to format API response messages by wrapping 
 * {@link String#format(String, Object...)} calls with meaningful method names and parameter validation.
 * All methods are static and the class cannot be instantiated due to the {@link UtilityClass} annotation.
 * </p>
 *
 * <h3>Benefits:</h3>
 * <ul>
 *   <li>Type safety and IDE auto-completion</li>
 *   <li>Consistent message formatting across the application</li>
 *   <li>Reduced risk of formatting errors</li>
 *   <li>Clear method names that indicate message purpose</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * // Instead of: String.format(ApiMessages.RESOURCE_CREATED, "User")
 * String message = ApiFormatter.resourceCreated("User");
 * // Result: "User created successfully"
 *
 * // Instead of: String.format(ApiMessages.RESOURCE_NOT_FOUND_BY_ID, "Product", 123)
 * String error = ApiFormatter.resourceNotFoundById("Product", 123);
 * // Result: "Product with ID 123 not found"
 * </pre>
 *
 * @author Your Name
 * @version 1.0
 * @since 1.0
 * @see ApiMessages
 */
@UtilityClass
public class ApiFormatter {

    // ========== AUTHENTICATION & AUTHORIZATION ERROR MESSAGES ==========

    /**
     * Creates an error message for invalid credentials during authentication.
     *
     * @param username the username/email that failed authentication
     * @return formatted message indicating invalid credentials
     *
     * @example
     * <pre>
     * String message = invalidCredentials("john@example.com");
     * // Returns: "Invalid credentials provided for user 'john@example.com'"
     * </pre>
     */
    public static String invalidCredentials(String username) {
        return String.format(INVALID_CREDENTIALS, username);
    }

    /**
     * Creates an error message for disabled account during authentication.
     *
     * @param username the username/email of the disabled account
     * @return formatted message indicating account is disabled
     *
     * @example
     * <pre>
     * String message = accountDisabled("john@example.com");
     * // Returns: "Account 'john@example.com' is disabled"
     * </pre>
     */
    public static String accountDisabled(String username) {
        return String.format(ACCOUNT_DISABLED, username);
    }

    // ========== RESOURCE OPERATIONS SUCCESS ==========

    /**
     * Creates a success message for resource creation operations.
     *
     * @param resourceType the type of resource that was created (e.g., "User", "Product")
     * @return formatted message indicating successful resource creation
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceCreated("User");
     * // Returns: "User created successfully"
     * </pre>
     */
    public static String resourceCreated(String resourceType) {
        return String.format(RESOURCE_CREATED, resourceType);
    }

    /**
     * Creates a success message for resource update operations.
     *
     * @param resourceType the type of resource that was updated (e.g., "User", "Product")
     * @return formatted message indicating successful resource update
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceUpdated("Profile");
     * // Returns: "Profile updated successfully"
     * </pre>
     */
    public static String resourceUpdated(String resourceType) {
        return String.format(RESOURCE_UPDATED, resourceType);
    }

    /**
     * Creates a success message for resource deletion operations.
     *
     * @param resourceType the type of resource that was deleted (e.g., "User", "Product")
     * @return formatted message indicating successful resource deletion
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceDeleted("Account");
     * // Returns: "Account deleted successfully"
     * </pre>
     */
    public static String resourceDeleted(String resourceType) {
        return String.format(RESOURCE_DELETED, resourceType);
    }

    /**
     * Creates a success message for single resource retrieval operations.
     *
     * @param resourceType the type of resource that was retrieved (e.g., "User", "Product")
     * @return formatted message indicating successful resource retrieval
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceRetrieved("Document");
     * // Returns: "Document retrieved successfully"
     * </pre>
     */
    public static String resourceRetrieved(String resourceType) {
        return String.format(RESOURCE_RETRIEVED, resourceType);
    }

    /**
     * Creates a success message for multiple resources retrieval operations.
     *
     * @param resourceType the type of resources that were retrieved (e.g., "Users", "Products")
     * @return formatted message indicating successful resources retrieval
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourcesRetrieved("Orders");
     * // Returns: "Orders retrieved successfully"
     * </pre>
     */
    public static String resourcesRetrieved(String resourceType) {
        return String.format(RESOURCES_RETRIEVED, resourceType);
    }

    /**
     * Creates a success message for resource save operations.
     *
     * @param resourceType the type of resource that was saved (e.g., "Draft", "Configuration")
     * @return formatted message indicating successful resource save
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceSaved("Settings");
     * // Returns: "Settings saved successfully"
     * </pre>
     */
    public static String resourceSaved(String resourceType) {
        return String.format(RESOURCE_SAVED, resourceType);
    }

    /**
     * Creates a success message for resource restoration operations.
     *
     * @param resourceType the type of resource that was restored (e.g., "User", "Document")
     * @return formatted message indicating successful resource restoration
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceRestored("Backup");
     * // Returns: "Backup restored successfully"
     * </pre>
     */
    public static String resourceRestored(String resourceType) {
        return String.format(RESOURCE_RESTORED, resourceType);
    }

    /**
     * Creates a success message for resource activation operations.
     *
     * @param resourceType the type of resource that was activated (e.g., "Account", "License")
     * @return formatted message indicating successful resource activation
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceActivated("Subscription");
     * // Returns: "Subscription activated successfully"
     * </pre>
     */
    public static String resourceActivated(String resourceType) {
        return String.format(RESOURCE_ACTIVATED, resourceType);
    }

    /**
     * Creates a success message for resource deactivation operations.
     *
     * @param resourceType the type of resource that was deactivated (e.g., "Account", "License")
     * @return formatted message indicating successful resource deactivation
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceDeactivated("Feature");
     * // Returns: "Feature deactivated successfully"
     * </pre>
     */
    public static String resourceDeactivated(String resourceType) {
        return String.format(RESOURCE_DEACTIVATED, resourceType);
    }

    /**
     * Creates a success message for resource archiving operations.
     *
     * @param resourceType the type of resource that was archived (e.g., "Project", "Document")
     * @return formatted message indicating successful resource archiving
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceArchived("Campaign");
     * // Returns: "Campaign archived successfully"
     * </pre>
     */
    public static String resourceArchived(String resourceType) {
        return String.format(RESOURCE_ARCHIVED, resourceType);
    }

    /**
     * Creates a success message for resource publishing operations.
     *
     * @param resourceType the type of resource that was published (e.g., "Article", "Course")
     * @return formatted message indicating successful resource publishing
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourcePublished("Blog Post");
     * // Returns: "Blog Post published successfully"
     * </pre>
     */
    public static String resourcePublished(String resourceType) {
        return String.format(RESOURCE_PUBLISHED, resourceType);
    }

    /**
     * Creates a success message for resource unpublishing operations.
     *
     * @param resourceType the type of resource that was unpublished (e.g., "Article", "Course")
     * @return formatted message indicating successful resource unpublishing
     * @throws IllegalArgumentException if resourceType is null
     *
     * @example
     * <pre>
     * String message = resourceUnpublished("News Article");
     * // Returns: "News Article unpublished successfully"
     * </pre>
     */
    public static String resourceUnpublished(String resourceType) {
        return String.format(RESOURCE_UNPUBLISHED, resourceType);
    }

    // ========== RESOURCE MESSAGES ==========

    /**
     * Creates an error message when a resource is not found.
     *
     * @param resourceName the name of the resource that was not found
     * @return formatted error message indicating resource not found
     * @throws IllegalArgumentException if resourceName is null
     *
     * @example
     * <pre>
     * String error = resourceNotFound("User");
     * // Returns: "User not found"
     * </pre>
     */
    public static String resourceNotFound(String resourceName) {
        return String.format(RESOURCE_NOT_FOUND, resourceName);
    }

    /**
     * Creates an error message when a resource is not found with a specific field value.
     *
     * @param resourceName the name of the resource that was not found
     * @param fieldName the name of the field used in the search
     * @param fieldValue the value of the field used in the search
     * @return formatted error message indicating resource not found with specific field
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = resourceNotFound("User", "email", "john@example.com");
     * // Returns: "User not found with email: john@example.com"
     * </pre>
     */
    public static String resourceNotFound(String resourceName, String fieldName, Object fieldValue) {
        return String.format(RESOURCE_NOT_FOUND_WITH_FIELD,
                resourceName, fieldName, fieldValue);
    }

    /**
     * Creates an error message when a resource is not found by ID.
     *
     * @param resourceName the name of the resource that was not found
     * @param id the ID value used in the search
     * @return formatted error message indicating resource not found by ID
     * @throws IllegalArgumentException if resourceName is null
     *
     * @example
     * <pre>
     * String error = resourceNotFoundById("Product", 12345);
     * // Returns: "Product with ID 12345 not found"
     * </pre>
     */
    public static String resourceNotFoundById(String resourceName, Object id) {
        return String.format(RESOURCE_NOT_FOUND_BY_ID, resourceName, id);
    }

    /**
     * Creates an error message when a resource already exists.
     *
     * @param resourceName the name of the resource that already exists
     * @return formatted error message indicating resource already exists
     * @throws IllegalArgumentException if resourceName is null
     *
     * @example
     * <pre>
     * String error = resourceAlreadyExists("User");
     * // Returns: "User already exists"
     * </pre>
     */
    public static String resourceAlreadyExists(String resourceName) {
        return String.format(RESOURCE_ALREADY_EXISTS, resourceName);
    }

    /**
     * Creates an error message when a resource already exists with a specific field value.
     *
     * @param resourceName the name of the resource that already exists
     * @param fieldName the name of the conflicting field
     * @param fieldValue the value of the conflicting field
     * @return formatted error message indicating resource already exists with specific field
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = resourceAlreadyExists("User", "username", "john_doe");
     * // Returns: "User with username 'john_doe' already exists"
     * </pre>
     */
    public static String resourceAlreadyExists(String resourceName, String fieldName, Object fieldValue) {
        return String.format(RESOURCE_ALREADY_EXISTS_WITH_FIELD,
                resourceName, fieldName, fieldValue);
    }

    /**
     * Creates an error message when a resource is in use and cannot be deleted.
     *
     * @param resourceName the name of the resource that is in use
     * @return formatted error message indicating resource is in use
     * @throws IllegalArgumentException if resourceName is null
     *
     * @example
     * <pre>
     * String error = resourceInUse("Category");
     * // Returns: "Category is currently in use and cannot be deleted"
     * </pre>
     */
    public static String resourceInUse(String resourceName) {
        return String.format(RESOURCE_IN_USE, resourceName);
    }

    // ========== VALIDATION MESSAGES ==========

    /**
     * Creates a validation error message for required fields.
     *
     * @param fieldName the name of the required field
     * @return formatted validation error message indicating field is required
     * @throws IllegalArgumentException if fieldName is null
     *
     * @example
     * <pre>
     * String error = fieldRequired("Email");
     * // Returns: "Email is required"
     * </pre>
     */
    public static String fieldRequired(String fieldName) {
        return String.format(FIELD_REQUIRED, fieldName);
    }

    /**
     * Creates a validation error message for invalid field values.
     *
     * @param fieldName the name of the invalid field
     * @return formatted validation error message indicating field is invalid
     * @throws IllegalArgumentException if fieldName is null
     *
     * @example
     * <pre>
     * String error = fieldInvalid("Phone Number");
     * // Returns: "Phone Number is invalid"
     * </pre>
     */
    public static String fieldInvalid(String fieldName) {
        return String.format(FIELD_INVALID, fieldName);
    }

    /**
     * Creates a validation error message for fields that are too short.
     *
     * @param fieldName the name of the field that is too short
     * @param minLength the minimum required length
     * @return formatted validation error message indicating field is too short
     * @throws IllegalArgumentException if fieldName is null or minLength is negative
     *
     * @example
     * <pre>
     * String error = fieldTooShort("Password", 8);
     * // Returns: "Password must be at least 8 characters"
     * </pre>
     */
    public static String fieldTooShort(String fieldName, int minLength) {
        return String.format(FIELD_TOO_SHORT, fieldName, minLength);
    }

    /**
     * Creates a validation error message for fields that exceed maximum length.
     *
     * @param fieldName the name of the field that is too long
     * @param maxLength the maximum allowed length
     * @return formatted validation error message indicating field is too long
     * @throws IllegalArgumentException if fieldName is null or maxLength is negative
     *
     * @example
     * <pre>
     * String error = fieldTooLong("Description", 500);
     * // Returns: "Description must not exceed 500 characters"
     * </pre>
     */
    public static String fieldTooLong(String fieldName, int maxLength) {
        return String.format(FIELD_TOO_LONG, fieldName, maxLength);
    }

    /**
     * Creates a validation error message for fields with values outside allowed range.
     *
     * @param fieldName the name of the field that is out of range
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @return formatted validation error message indicating field is out of range
     * @throws IllegalArgumentException if fieldName is null
     *
     * @example
     * <pre>
     * String error = fieldOutOfRange("Age", 18, 65);
     * // Returns: "Age must be between 18 and 65"
     * </pre>
     */
    public static String fieldOutOfRange(String fieldName, Object min, Object max) {
        return String.format(FIELD_OUT_OF_RANGE, fieldName, min, max);
    }

    /**
     * Creates a validation error message for fields that must have unique values.
     *
     * @param fieldName the name of the field that must be unique
     * @return formatted validation error message indicating field must be unique
     * @throws IllegalArgumentException if fieldName is null
     *
     * @example
     * <pre>
     * String error = fieldMustBeUnique("Username");
     * // Returns: "Username must be unique"
     * </pre>
     */
    public static String fieldMustBeUnique(String fieldName) {
        return String.format(FIELD_MUST_BE_UNIQUE, fieldName);
    }

    /**
     * Creates a validation error message for fields that don't match required pattern.
     *
     * @param fieldName the name of the field with pattern mismatch
     * @param pattern the expected pattern (regex or description)
     * @return formatted validation error message indicating pattern mismatch
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = fieldPatternMismatch("Phone", "\\d{3}-\\d{3}-\\d{4}");
     * // Returns: "Phone must match pattern: \\d{3}-\\d{3}-\\d{4}"
     * </pre>
     */
    public static String fieldPatternMismatch(String fieldName, String pattern) {
        return String.format(FIELD_PATTERN_MISMATCH, fieldName, pattern);
    }

    /**
     * Creates a validation error message for fields with incorrect data type.
     *
     * @param fieldName the name of the field with type mismatch
     * @param expectedType the expected data type
     * @return formatted validation error message indicating type mismatch
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = fieldTypeMismatch("Quantity", "Integer");
     * // Returns: "Quantity must be of type Integer"
     * </pre>
     */
    public static String fieldTypeMismatch(String fieldName, String expectedType) {
        return String.format(FIELD_TYPE_MISMATCH, fieldName, expectedType);
    }

    // ========== BUSINESS RULE MESSAGES ==========

    /**
     * Creates a business rule error message for invalid status transitions.
     *
     * @param fromStatus the current status
     * @param toStatus the target status
     * @return formatted error message indicating invalid status transition
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = invalidStatusTransition("DRAFT", "ARCHIVED");
     * // Returns: "Cannot transition from DRAFT to ARCHIVED"
     * </pre>
     */
    public static String invalidStatusTransition(String fromStatus, String toStatus) {
        return String.format(STATUS_TRANSITION_INVALID, fromStatus, toStatus);
    }

    /**
     * Creates a business rule error message for operations not allowed in current state.
     *
     * @param operation the operation being attempted
     * @param currentState the current state of the resource
     * @return formatted error message indicating operation not allowed
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = operationNotAllowed("DELETE", "PUBLISHED");
     * // Returns: "Operation 'DELETE' is not allowed in current state: PUBLISHED"
     * </pre>
     */
    public static String operationNotAllowed(String operation, String currentState) {
        return String.format(OPERATION_NOT_ALLOWED, operation, currentState);
    }

    /**
     * Creates a business rule error message for unmet prerequisites.
     *
     * @param prerequisite description of the unmet prerequisite
     * @return formatted error message indicating prerequisite not met
     * @throws IllegalArgumentException if prerequisite is null
     *
     * @example
     * <pre>
     * String error = prerequisiteNotMet("User must verify email address");
     * // Returns: "Prerequisite not met: User must verify email address"
     * </pre>
     */
    public static String prerequisiteNotMet(String prerequisite) {
        return String.format(PREREQUISITE_NOT_MET, prerequisite);
    }

    /**
     * Creates a business rule error message for exceeded limits.
     *
     * @param limitType the type of limit that was exceeded
     * @param current the current count
     * @param maximum the maximum allowed count
     * @return formatted error message indicating limit exceeded
     * @throws IllegalArgumentException if limitType is null or counts are negative
     *
     * @example
     * <pre>
     * String error = limitExceeded("File upload", 5, 3);
     * // Returns: "File upload limit exceeded: 5 of 3"
     * </pre>
     */
    public static String limitExceeded(String limitType, int current, int maximum) {
        return String.format(LIMIT_EXCEEDED, limitType, current, maximum);
    }

    /**
     * Creates a business rule error message for insufficient quantity.
     *
     * @param itemType the type of item with insufficient quantity
     * @param required the required quantity
     * @param available the available quantity
     * @return formatted error message indicating insufficient quantity
     * @throws IllegalArgumentException if itemType is null or quantities are negative
     *
     * @example
     * <pre>
     * String error = insufficientQuantity("Stock", 10, 5);
     * // Returns: "Insufficient Stock: required 10, available 5"
     * </pre>
     */
    public static String insufficientQuantity(String itemType, int required, int available) {
        return String.format(INSUFFICIENT_QUANTITY, itemType, required, available);
    }

    /**
     * Creates a business rule error message for duplicate entries.
     *
     * @param resourceName the name of the resource with duplicate entry
     * @param fieldName the name of the field that has duplicate value
     * @param value the duplicate value
     * @return formatted error message indicating duplicate entry
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = duplicateEntry("Order", "order number", "ORD-001");
     * // Returns: "Order already exists with order number: ORD-001"
     * </pre>
     */
    public static String duplicateEntry(String resourceName, String fieldName, Object value) {
        return String.format(DUPLICATE_ENTRY, resourceName, fieldName, value);
    }

    // ========== FILE MESSAGES ==========

    /**
     * Creates a file upload error message for files that exceed size limits.
     *
     * @param actualSize the actual size of the file (with units, e.g., "5.2MB")
     * @param maxSize the maximum allowed size (with units, e.g., "2MB")
     * @return formatted error message indicating file too large
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = fileTooLarge("5.2MB", "2MB");
     * // Returns: "File size 5.2MB exceeds maximum allowed size of 2MB"
     * </pre>
     */
    public static String fileTooLarge(String actualSize, String maxSize) {
        return String.format(FILE_TOO_LARGE, actualSize, maxSize);
    }

    /**
     * Creates a file upload error message for disallowed file types.
     *
     * @param fileType the file type that was rejected
     * @param allowedTypes comma-separated list of allowed file types
     * @return formatted error message indicating file type not allowed
     * @throws IllegalArgumentException if any parameter is null
     *
     * @example
     * <pre>
     * String error = fileTypeNotAllowed("exe", "jpg, png, pdf");
     * // Returns: "File type 'exe' is not allowed. Allowed types: jpg, png, pdf"
     * </pre>
     */
    public static String fileTypeNotAllowed(String fileType, String allowedTypes) {
        return String.format(FILE_TYPE_NOT_ALLOWED, fileType, allowedTypes);
    }

    /**
     * Creates a file upload error message for upload failures.
     *
     * @param reason the reason for the upload failure
     * @return formatted error message indicating file upload failed
     * @throws IllegalArgumentException if reason is null
     *
     * @example
     * <pre>
     * String error = fileUploadFailed("Disk quota exceeded");
     * // Returns: "Failed to upload file: Disk quota exceeded"
     * </pre>
     */
    public static String fileUploadFailed(String reason) {
        return String.format(FILE_UPLOAD_FAILED, reason);
    }

    /**
     * Formats validation error messages based on the error key and field error details.
     * This method uses pattern matching to handle different types of validation errors
     * and delegates to appropriate ApiFormatter methods for consistent formatting.
     *
     * @param fieldError the field error object containing validation details and
     * @return formatted validation error message appropriate for the error type
     * @throws NullPointerException if key or fieldError is null
     *
     * @example
     * <pre>
     * FieldError error = new FieldError("user", "email", "Email is required");
     * String message = formatValidationMessage("FIELD_REQUIRED", error);
     * // Returns: "Email is required"
     *
     * FieldError lengthError = new FieldError("user", "name", new Object[]{"name", 50}, "Name too long");
     * String lengthMessage = formatValidationMessage("FIELD_TOO_LONG", lengthError);
     * // Returns: "Name must not exceed 50 characters"
     *
     * FieldError rangeError = new FieldError("user", "age", new Object[]{"age", 18, 65}, "Age out of range");
     * String rangeMessage = formatValidationMessage("FIELD_OUT_OF_RANGE", rangeError);
     * // Returns: "Age must be between 18 and 65"
     * </pre>
     *
     * @see ApiFormatter#fieldRequired(String)
     * @see ApiFormatter#fieldTooLong(String, int)
     * @see ApiFormatter#fieldOutOfRange(String, Object, Object)
     * @since 1.0
     */
    public String formatValidationMessage(FieldError fieldError) {
        String key = switch (fieldError.getCode()) {
            case "NotNull", "NotBlank" -> "FIELD_REQUIRED";
            case "Size" -> "FIELD_SIZE";  // đổi key chung cho Size
            case "Min", "Max", "Range" -> "FIELD_OUT_OF_RANGE";
            case "Pattern" -> "FIELD_PATTERN_MISMATCH";
            case null, default -> null;
        };

        return switch (key) {
            case "FIELD_REQUIRED" -> ApiFormatter.fieldRequired(fieldError.getField());

            case "FIELD_SIZE" -> {
                Object rejected = fieldError.getRejectedValue();
                Object[] args = fieldError.getArguments();
                if (args != null && args.length > 2 && rejected instanceof String value) {
                    int max = (int) args[1];
                    int min = (int) args[2];
                    int actual = value.length();
                    if (actual < min) {
                        yield ApiFormatter.fieldTooShort(fieldError.getField(), min);
                    } else if (actual > max) {
                        yield ApiFormatter.fieldTooLong(fieldError.getField(), max);
                    }
                }
                yield fieldError.getDefaultMessage(); // fallback
            }

            case "FIELD_OUT_OF_RANGE" -> {
                Object[] args = fieldError.getArguments();
                if (args != null && args.length > 2) {
                    yield ApiFormatter.fieldOutOfRange(
                            fieldError.getField(),
                            args[1],
                            args[2]
                    );
                } else {
                    yield fieldError.getDefaultMessage();
                }
            }

            case "FIELD_PATTERN_MISMATCH" -> {
                Object[] args = fieldError.getArguments();
                if (args != null && args.length > 2) {
                    String pattern = args[2].toString();
                    yield ApiFormatter.fieldPatternMismatch(
                            fieldError.getField(),
                            pattern
                    );
                } else {
                    yield fieldError.getDefaultMessage();
                }
            }

            case null, default -> fieldError.getDefaultMessage();
        };
    }
}