package com.fernirx.lms.infrastructure.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernirx.lms.common.constants.ApiConstants;
import com.fernirx.lms.common.dtos.responses.ErrorResponse;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.TokenException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(ApiConstants.CONTENT_TYPE_JSON);
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        ErrorResponse errorResponse = null;
        if (authException instanceof TokenException ex) {
            ErrorCode errorCode = ex.getErrorCode();
            errorResponse = ErrorResponse.of(errorCode, ex.getMessage());
            statusCode = errorCode.getHttpStatus().value();
        }
        response.setStatus(statusCode);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();
    }
}
