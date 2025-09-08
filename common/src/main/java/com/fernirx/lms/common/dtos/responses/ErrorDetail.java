package com.fernirx.lms.common.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fernirx.lms.common.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "ErrorDetail",
        title = "Error Detail",
        description = "Detailed information about a specific error that occurred in the system, typically related to field-level validation"
)
public class ErrorDetail {
    @Schema(
            description = "Specific error code from the system's ErrorCode enum",
            example = "FIELD_REQUIRED",
            implementation = ErrorCode.class
    )
    private ErrorCode code;

    @Schema(
            description = "Detailed error message, either default from ErrorCode or custom message",
            example = "Username is required",
            minLength = 1,
            maxLength = 500
    )
    private String message;

    @Schema(
            description = "Name of the field that caused the error (for validation errors). Null if error is not field-specific",
            example = "username"
    )
    private String field;

    @Schema(
            description = "The value that was rejected during validation",
            example = "abc"
    )
    private String rejectedValue;

    public static ErrorDetail of(ErrorCode errorCode, String customMessage, String field,  Object rejectedValue) {
        return ErrorDetail.builder()
                .code(errorCode)
                .message(customMessage)
                .field(field)
                .rejectedValue(Objects.toString(rejectedValue, null))
                .build();
    }
}
