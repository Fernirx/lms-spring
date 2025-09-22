package com.fernirx.lms.auth.service;

import com.fernirx.lms.auth.dto.OTPData;
import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.constants.SecurityConstants;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.exceptions.OtpException;
import com.fernirx.lms.infrastructure.properties.OtpProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final CacheManager cacheManager;
    private final OtpProperties otpProperties;
    private SecureRandom secureRandom;
    private Cache cache;

    @PostConstruct
    public void init() {
        this.secureRandom = new SecureRandom();
        this.cache = cacheManager.getCache("otps");
    }

    public OTPData generateOtp(String key) {
        validateKey(key);

        String otp = generateRandomOtp();
        Instant expireAt = Instant.now().plus(otpProperties.getExpireAfterWrite());
        Instant lastResetAt = Instant.now().plus(otpProperties.getResendCooldown());

        OTPData otpData = OTPData.builder()
                .otp(otp)
                .expireAt(expireAt)
                .maxAttempts(0)
                .resendCount(0)
                .lastResendAt(lastResetAt)
                .build();

        cache.put(key, otpData);
        return otpData;
    }

    public OTPData regenerateOtp(String key) {
        validateKey(key);
        OTPData existingOtp = cache.get(key, OTPData.class);
        if (existingOtp != null) {
            if (existingOtp.getResendCount() >= otpProperties.getMaxResend()) {
                cache.evict(key);
                throw new OtpException(ErrorCode.OTP_MAX_RESEND_EXCEEDED, ApiMessages.OTP_MAX_RESEND_EXCEEDED);
            }
            if (existingOtp.getLastResendAt().isAfter(Instant.now())) {
                throw new OtpException(ErrorCode.OTP_RESEND_COOLDOWN, ApiMessages.OTP_RESEND_COOLDOWN);
            }
            Instant expireAt = Instant.now().plus(otpProperties.getExpireAfterWrite());
            Instant lastResetAt = Instant.now().plus(otpProperties.getResendCooldown());

            existingOtp.setOtp(generateRandomOtp());
            existingOtp.incrementResendCount();
            existingOtp.setExpireAt(expireAt);
            existingOtp.setLastResendAt(lastResetAt);

            cache.put(key, existingOtp);
            return existingOtp;
        }
        throw new OtpException(ErrorCode.OTP_NOT_FOUND, ApiMessages.OTP_NOT_FOUND);
    }

    public Boolean validateOtp(String key, String inputOtp) {
        OTPData otpData = cache.get(key, OTPData.class);
        if (otpData == null) {
            throw new OtpException(ErrorCode.OTP_NOT_FOUND, ApiMessages.OTP_NOT_FOUND);
        }
        if (otpData.getExpireAt().isBefore(Instant.now())) {
            cache.evict(key);
            throw new OtpException(ErrorCode.OTP_EXPIRED, ApiMessages.OTP_EXPIRED);
        }
        if (Objects.equals(otpData.getMaxAttempts(), otpProperties.getMaxAttempts())) {
            cache.evict(key);
            throw new OtpException(ErrorCode.OTP_MAX_ATTEMPTS_EXCEED, ApiMessages.OTP_MAX_ATTEMPTS_EXCEED);
        }

        otpData.incrementAttempts();
        cache.put(key, otpData);

        if (!otpData.getOtp().equalsIgnoreCase(inputOtp)) {
            throw new OtpException(ErrorCode.OTP_INVALID, ApiMessages.OTP_INVALID);
        }

        cache.evict(key);
        return true;
    }

    private String generateRandomOtp() {
        int otpValue = secureRandom.nextInt((int) Math.pow(10, SecurityConstants.OTP_LENGTH));
        return String.format("%0" + SecurityConstants.OTP_LENGTH + "d", otpValue);
    }

    private void validateKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("OTP key cannot be null or empty");
        }
    }
}
