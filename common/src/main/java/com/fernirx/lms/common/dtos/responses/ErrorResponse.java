package com.fernirx.lms.common.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fernirx.lms.common.enums.ErrorCategory;
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
@Schema(name = "Error Response", description = "Error API response wrapper")
public class ErrorResponse {
    @Schema(description = "Timestamp of error", example = "2025-08-04T20:00:00+07:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime timestamp;

    @Schema(description = "Error category", example = "VALIDATION", implementation = ErrorCategory.class)
    private ErrorCategory category;

    @Schema(description = "Detailed error message", example = "Invalid input parameters")
    private String message;

    @Schema(description = "List of field-specific errors")
    private List<ErrorDetail> errors;

    public static ErrorResponse of(ErrorCategory category, String message, List<ErrorDetail> errors) {
        return ErrorResponse.builder()
                .timestamp(ZonedDateTime.now())
                .category(category)
                .message(message)
                .errors(errors)
                .build();
    }

    public static ErrorResponse of(ErrorCategory category, String message, ErrorDetail error) {
        return of(category, message, List.of(error));
    }
}
