package com.fernirx.lms.common.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fernirx.lms.common.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "ErrorResponse",
        title = "Error Response",
        description = "Standard error response structure for all API errors, providing comprehensive error information"
)
public class ErrorResponse {
    @Schema(
            description = "ISO 8601 timestamp indicating when the error occurred",
            example = "2025-08-04T20:00:00+07:00"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime timestamp;

    @Schema(
            description = "Main error code representing the type of error that occurred",
            example = "VALIDATION_ERROR",
            implementation = ErrorCode.class
    )
    private ErrorCode code;

    @Schema(
            description = "Human-readable error message describing what went wrong",
            example = "Invalid input parameters provided",
            minLength = 1,
            maxLength = 500
    )
    private String message;

    @Schema(
            description = "List of detailed error information, typically for field-specific validation errors. Can be null or empty for single errors",
            nullable = true
    )
    private List<ErrorDetail> details;

    public static ErrorResponse of(ErrorCode code, String message, List<ErrorDetail> details) {
        return ErrorResponse.builder()
                .timestamp(ZonedDateTime.now())
                .code(code)
                .message(message)
                .details(details)
                .build();
    }

    public static ErrorResponse of(ErrorCode code, String message) {
        return of(code, message, null);
    }
}
