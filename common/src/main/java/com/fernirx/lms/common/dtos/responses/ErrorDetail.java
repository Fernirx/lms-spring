package com.fernirx.lms.common.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fernirx.lms.common.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Error Detail", description = "Specific details of the error in the system")
public class ErrorDetail {
    @Schema(description = "Error code", example = "ERR_VALIDATION")
    private ErrorCode code;

    @Schema(description = "Error message", example = "Username is required")
    private String message;

    @Schema(description = "Field name (if applicable)", example = "username")
    private String field;

    public static ErrorDetail of(ErrorCode errorCode, String field) {
        return of(errorCode, errorCode.getMessage(), field);
    }

    public static ErrorDetail of(ErrorCode errorCode) {
        return of(errorCode, null);
    }

    public static ErrorDetail of(ErrorCode errorCode, String customMessage, String field) {
        return ErrorDetail.builder()
                .code(errorCode)
                .message(customMessage)
                .field(field)
                .build();
    }
}
