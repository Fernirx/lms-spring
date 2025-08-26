package com.fernirx.lms.infrastructure.security;

import com.fernirx.lms.common.constants.SecurityConstants;
import com.fernirx.lms.common.exceptions.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String generateRefreshToken(int userId, String username) {
        return createToken(
                SecurityConstants.JWT_REFRESH_TOKEN,
                userId,
                username,
                null,
                jwtProperties.getRefreshExpiration()
        );
    }

    public String refreshAccessToken(String accessToken, String refreshToken) {
        validateRefreshToken(refreshToken);

        int userId = Integer.parseInt(extractSubject(refreshToken));
        String username = extractUsername(refreshToken);
        List<String> authorities = extractAuthoritiesIgnoreExpiry(accessToken);

        return createToken(
                SecurityConstants.JWT_ACCESS_TOKEN,
                userId,
                username,
                authorities,
                jwtProperties.getExpiration()
        );
    }

    public String rotateRefreshToken(String oldRefreshToken) {
        validateRefreshToken(oldRefreshToken);

        int userId = Integer.parseInt(extractSubject(oldRefreshToken));
        String username = extractUsername(oldRefreshToken);

        return generateRefreshToken(userId, username);
    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, SecurityConstants.JWT_ACCESS_TOKEN);
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token, SecurityConstants.JWT_REFRESH_TOKEN);
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

    public List<String> extractAuthorities(String token) {
        Object authorities = extractAllClaims(token).get(SecurityConstants.JWT_CLAIMS_AUTHORITIES);
        if (authorities instanceof List<?>) {
            return ((List<?>) authorities).stream()
                    .map(Object::toString)
                    .toList();
        }
        return List.of();
    }

    public List<String> extractAuthoritiesIgnoreExpiry(String token) {
        Object authorities = extractAllClaimsIgnoreExpiry(token).get(SecurityConstants.JWT_CLAIMS_AUTHORITIES);
        if (authorities instanceof List<?>) {
            return ((List<?>) authorities).stream()
                    .map(Object::toString)
                    .toList();
        }
        return List.of();
    }

    // ==== PRIVATE HELPERS ====

    private String createToken(String type, int userId, String username,
                               List<String> authorities, long expirationMillis) {
        Map<String, Object> claims =buildClaims(type, username, authorities);
        return buildJwtToken(String.valueOf(userId), claims, expirationMillis);
    }

    private String buildJwtToken(String subject, Map<String, Object> claims, long expirationMillis) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMillis);
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expirationDate)
                .issuer(jwtProperties.getIssuer())
                .signWith(key)
                .compact();
    }

    private Map<String, Object> buildClaims(String type, String username,  List<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SecurityConstants.JWT_CLAIMS_TYPE, type);
        claims.put(SecurityConstants.JWT_CLAIMS_USERNAME, username);
        if (authorities != null) {
            claims.put(SecurityConstants.JWT_CLAIMS_AUTHORITIES, authorities);
        }
        return claims;
    }

    private boolean validateToken(String token, String expectedType) {
        try {
            Claims claims = extractAllClaims(token);
            String tokenType = claims.get(SecurityConstants.JWT_CLAIMS_TYPE).toString();

            if (!expectedType.equals(tokenType)) {
                throw new InvalidTokenTypeException(expectedType, tokenType);
            }

            return true;
        } catch (JwtException e) {
            handleJwtException(e);
            return false;
        }
    }

    private void handleJwtException(JwtException e) {
        switch (e) {
            case ExpiredJwtException ex -> throw new ExpiredTokenException();
            case MalformedJwtException ex -> throw new MalformedTokenException();
            case UnsupportedJwtException ex -> throw new UnsupportedTokenException();
            case io.jsonwebtoken.security.SecurityException ex -> throw new InvalidTokenException();
            default -> throw new JwtValidationException();
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Claims extractAllClaimsIgnoreExpiry(String token) {
        try {
            return extractAllClaims(token);
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
