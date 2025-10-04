package com.fernirx.lms.infrastructure.security;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.constants.SecurityConstants;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.*;
import com.fernirx.lms.infrastructure.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(
                        jwtProperties.getSecret()
                )
        );
    }

    // ==== PUBLIC API ====

    public String generateAccessToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return createToken(
                SecurityConstants.JWT_ACCESS_TOKEN,
                userDetails.getId(),
                userDetails.getUsername(),
                SecurityUtils.getAuthorities(userDetails),
                jwtProperties.getExpiration()
        );
    }

    public String generateRefreshToken(long userId, String username) {
        return createToken(
                SecurityConstants.JWT_REFRESH_TOKEN,
                userId,
                username,
                null,
                jwtProperties.getRefreshExpiration()
        );
    }

    public String generateResetPasswordToken(long userId, String username) {
        return createToken(
                SecurityConstants.JWT_RESET_PASSWORD_TOKEN,
                userId,
                username,
                null,
                jwtProperties.getResetPasswordExpiration()
        );
    }

    public String refreshAccessToken(String refreshToken, CustomUserDetails userDetails) {
        validateRefreshToken(refreshToken);

        return createToken(
                SecurityConstants.JWT_ACCESS_TOKEN,
                userDetails.getId(),
                userDetails.getUsername(),
                SecurityUtils.getAuthorities(userDetails),
                jwtProperties.getExpiration()
        );
    }

    public String rotateRefreshToken(String oldRefreshToken) {
        validateRefreshToken(oldRefreshToken);

        long userId = Long.parseLong(extractSubject(oldRefreshToken));
        String username = extractUsername(oldRefreshToken);

        return generateRefreshToken(userId, username);
    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, SecurityConstants.JWT_ACCESS_TOKEN);
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token, SecurityConstants.JWT_REFRESH_TOKEN);
    }

    public boolean validateResetPasswordToken(String token) {
        return validateToken(token, SecurityConstants.JWT_RESET_PASSWORD_TOKEN);
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = extractAllClaims(token).getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public String extractSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractUsername(String token) {
        Object username = extractAllClaims(token).get(SecurityConstants.JWT_CLAIMS_USERNAME);
        return username != null ? username.toString() : null;
    }

    public Set<String> extractAuthorities(String token) {
        Object authorities = extractAllClaims(token).get(SecurityConstants.JWT_CLAIMS_AUTHORITIES);
        if (authorities instanceof Collection<?>) {
            return ((Collection<?>) authorities).stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }

    public Set<String> extractAuthoritiesIgnoreExpiry(String token) {
        Object authorities = extractAllClaimsIgnoreExpiry(token).get(SecurityConstants.JWT_CLAIMS_AUTHORITIES);
        if (authorities instanceof Collection<?>) {
            return ((Collection<?>) authorities).stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }

    // ==== PRIVATE HELPERS ====

    private String createToken(String type, long userId, String username,
                               Set<String> authorities, Duration expiration) {
        Map<String, Object> claims = buildClaims(type, username, authorities);
        return buildJwtToken(String.valueOf(userId), claims, expiration);
    }

    private String buildJwtToken(String subject, Map<String, Object> claims, Duration expiration) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration.toMillis());
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expirationDate)
                .issuer(jwtProperties.getIssuer())
                .signWith(key)
                .compact();
    }

    private Map<String, Object> buildClaims(String type, String username, Set<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SecurityConstants.JWT_CLAIMS_TYPE, type);
        claims.put(SecurityConstants.JWT_CLAIMS_USERNAME, username);
        if (authorities != null) {
            claims.put(SecurityConstants.JWT_CLAIMS_AUTHORITIES, authorities);
        }
        return claims;
    }

    private boolean validateToken(String token, String expectedType) {
        Claims claims = extractAllClaims(token);
        String tokenType = claims.get(SecurityConstants.JWT_CLAIMS_TYPE).toString();

        if (!expectedType.equals(tokenType)) {
            throw new TokenException(ErrorCode.INVALID_TOKEN_TYPE, ApiMessages.INVALID_TOKEN_TYPE);
        }

        return true;
    }

    private void handleJwtException(JwtException e) {
        switch (e) {
            case ExpiredJwtException ex -> throw new TokenException(ErrorCode.TOKEN_EXPIRED, ApiMessages.TOKEN_EXPIRED);
            case MalformedJwtException ex -> throw new TokenException(ErrorCode.MALFORMED_TOKEN, ApiMessages.TOKEN_MALFORMED);
            case UnsupportedJwtException ex -> throw new TokenException(ErrorCode.UNSUPPORTED_TOKEN, ApiMessages.TOKEN_UNSUPPORTED);
            case io.jsonwebtoken.security.SecurityException ex -> throw new TokenException(ErrorCode.TOKEN_INVALID, ApiMessages.TOKEN_INVALID);
            default -> throw new TokenException(ErrorCode.JWT_VALIDATION_FAILED, ApiMessages.TOKEN_VALIDATION_FAILED);
        }
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            handleJwtException(e);
            throw new IllegalStateException("Unreachable");
        }
    }

    private Claims extractAllClaimsIgnoreExpiry(String token) {
        try {
            return extractAllClaims(token);
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
