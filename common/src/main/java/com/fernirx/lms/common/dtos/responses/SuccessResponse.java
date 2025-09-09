package com.fernirx.lms.common.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "SuccessResponse",
        title = "Success Response",
        description = "Standard success response wrapper for all successful API operations, containing optional data payload"
)
public class SuccessResponse<T> {
    @Schema(
            description = "ISO 8601 timestamp indicating when the successful response was generated",
            example = "2025-08-04T20:00:00+07:00"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime timestamp;

    @Schema(
            description = "Human-readable success message describing the completed operation",
            example = "User successfully created",
            minLength = 1,
            maxLength = 500
    )
    private String message;

    @Schema(
            description = "Response payload containing the requested data. Can be any type (object, array, primitive) or null for operations without return data",
            nullable = true,
            anyOf = {Object.class, String.class, Number.class, Boolean.class}
    )
    private T data;

    public static <T> SuccessResponse<T> of(String message, T data) {
        return SuccessResponse.<T>builder()
                .timestamp(ZonedDateTime.now())
                .message(message)
                .data(data)
                .build();
    }

    public static SuccessResponse<Void> of(String message) {
        return SuccessResponse.<Void>builder()
                .timestamp(ZonedDateTime.now())
                .message(message)
                .build();
    }
}
