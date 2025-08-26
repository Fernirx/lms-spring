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
@Schema(name = "Success Response", description = "Success API response wrapper")
public class SuccessResponse<T> {
    @Schema(description = "Timestamp of response", example = "2025-08-04T20:00:00+07:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime timestamp;

    @Schema(description = "Human-readable success message", example = "Operation successful")
    private String message;

    @Schema(description = "Response payload data")
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
