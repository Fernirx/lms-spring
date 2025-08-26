package com.fernirx.lms.application;

import com.fernirx.lms.infrastructure.security.CustomUserDetails;
import com.fernirx.lms.infrastructure.security.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtProviderTest {

    private Authentication authentication;

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void contextLoads() {
        assertNotNull(jwtProvider);
    }

    @BeforeEach
    void beforeEach() {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        CustomUserDetails userDetails =
                new CustomUserDetails(1, "username", "password", List.of(grantedAuthority));
        authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Test
    void testGenerateAndValidateAccessToken() {
        String accessToken = jwtProvider.generateAccessToken(authentication);
        System.out.println("Access Token: " + accessToken);

        assertNotNull(accessToken);
        assertTrue(jwtProvider.validateAccessToken(accessToken));
        assertEquals(1, Integer.parseInt(jwtProvider.extractSubject(accessToken)));
        assertEquals("username", jwtProvider.extractUsername(accessToken));
        assertTrue(jwtProvider.extractAuthorities(accessToken).contains("ROLE_USER"));
    }

    @Test
    void testGenerateAndValidateRefreshToken() {
        String refreshToken = jwtProvider.generateRefreshToken(1, "username");
        System.out.println("Refresh Token: " + refreshToken);

        assertNotNull(refreshToken);
        assertTrue(jwtProvider.validateRefreshToken(refreshToken));
        assertEquals(1, Integer.parseInt(jwtProvider.extractSubject(refreshToken)));
        assertEquals("username", jwtProvider.extractUsername(refreshToken));
    }

    @Test
    void testRefreshAccessToken() throws InterruptedException {
        String oldAccessToken = jwtProvider.generateAccessToken(authentication);
        String refreshToken = jwtProvider.generateRefreshToken(1, "username");
        Thread.sleep(1000);
        String newAccessToken = jwtProvider.refreshAccessToken(oldAccessToken, refreshToken);
        System.out.println("Old Access Token: " + oldAccessToken);
        System.out.println("Refresh Token: " + refreshToken);
        System.out.println("New Access Token: " + newAccessToken);

        assertNotNull(newAccessToken);
        assertTrue(jwtProvider.validateAccessToken(newAccessToken));
        assertEquals(1, Integer.parseInt(jwtProvider.extractSubject(newAccessToken)));
        assertEquals("username", jwtProvider.extractUsername(newAccessToken));
        assertTrue(jwtProvider.extractAuthorities(newAccessToken).contains("ROLE_USER"));
        assertNotEquals(oldAccessToken, newAccessToken);
    }

    @Test
    void testRotateRefreshToken() {
        String refreshToken = jwtProvider.generateRefreshToken(1, "username");
        String rotateRefreshToken = jwtProvider.rotateRefreshToken(refreshToken);
        System.out.println("Refresh Token: " + refreshToken);
        System.out.println("Rotate Refresh Token: " + rotateRefreshToken);

        assertNotNull(rotateRefreshToken);
        assertEquals(refreshToken, rotateRefreshToken);
        assertEquals(1, Integer.parseInt(jwtProvider.extractSubject(rotateRefreshToken)));
        assertEquals("username", jwtProvider.extractUsername(rotateRefreshToken));
        assertTrue(jwtProvider.validateRefreshToken(rotateRefreshToken));
    }

    @Test
    void testTokenExpiration() throws InterruptedException {
        String accessToken = jwtProvider.generateAccessToken(authentication);
        Thread.sleep(10000);
        assertTrue(jwtProvider.isTokenExpired(accessToken));
    }
}
