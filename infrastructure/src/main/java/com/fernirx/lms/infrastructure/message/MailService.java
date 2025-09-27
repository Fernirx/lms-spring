package com.fernirx.lms.infrastructure.message;

import com.fernirx.lms.infrastructure.properties.FrontendProperties;
import com.fernirx.lms.infrastructure.properties.OtpProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final OtpProperties otpProperties;
    private final FrontendProperties frontendProperties;

    @Async
    public void sendMail(String to, String subject, String htmlContent) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            log.info("Email sent successfully to: {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send email to: {}", to, e);
        }
    }

    @Async
    public void sendResetPassword(String to, String userName, String otpCode) {
        String subject = "LMS System - Password Reset OTP";
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("otpCode", otpCode);
        context.setVariable("expirationMinutes", otpProperties.getExpireAfterWrite().toMinutes());
        String htmlContent = templateEngine.process("emails/reset-password-otp", context);
        sendMail(to, subject, htmlContent);
    }

    @Async
    public void sendWelcomeEmail(String to, String username, String newPassword) {
        String subject = "Welcome to LMS System";
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("username", username);
        context.setVariable("temporaryPassword", newPassword);
        context.setVariable("loginUrl", frontendProperties.getLoginUrl());
        String htmlContent = templateEngine.process("emails/new-user-account", context);
        sendMail(to, subject, htmlContent);
    }
}