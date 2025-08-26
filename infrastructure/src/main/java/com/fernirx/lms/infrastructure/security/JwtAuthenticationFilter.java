package com.fernirx.lms.infrastructure.security;

import com.fernirx.lms.common.constants.ApiConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final List<String> SKIP_PATHS = List.of(
            ApiConstants.AUTH_PATH + ApiConstants.LOGIN_PATH,
            ApiConstants.AUTH_PATH + ApiConstants.REFRESH_TOKEN_PATH
    );

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    // ==== Filter ====

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (shouldSkipAuthentication(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractJwtToken(request);
        if (isValidToken(token)) {
            setAuthenticationContext(token, request);
        }
        filterChain.doFilter(request, response);
    }

    // ==== PRIVATE HELPERS ====

    private boolean shouldSkipAuthentication(String path) {
        return SKIP_PATHS.contains(path);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String headerAuth = request.getHeader(ApiConstants.AUTHORIZATION_HEADER);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(ApiConstants.BEARER_PREFIX)) {
            return headerAuth.substring(ApiConstants.BEARER_PREFIX_LENGTH);
        }
        return null;
    }

    private boolean isValidToken(String token) {
        return token != null && jwtProvider.validateAccessToken(token);
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        String username = jwtProvider.extractUsername(token);
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
