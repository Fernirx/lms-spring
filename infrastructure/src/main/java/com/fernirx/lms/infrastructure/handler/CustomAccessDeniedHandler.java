package com.fernirx.lms.infrastructure.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.dtos.responses.ErrorResponse;
import com.fernirx.lms.common.enums.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;
        String message = ApiMessages.ACCESS_DENIED_RESOURCE;
        int statusCode = errorCode.getHttpStatus().value();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, message);
        response.setContentType(ApiConstants.CONTENT_TYPE_JSON);
        response.setStatus(statusCode);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();
    }
}
