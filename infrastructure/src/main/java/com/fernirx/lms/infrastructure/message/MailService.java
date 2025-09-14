package com.fernirx.lms.infrastructure.message;

import com.fernirx.lms.common.utils.EmailFormatter;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Async
    public void sendResetPassword(String to, String newPassword) {
        String subject = "Reset Password";
        String body = EmailFormatter.passwordReset(to, newPassword);
        sendMail(to, subject, body);
    }

    @Async
    public void sendNewAccount(String to, String username, String newPassword) {
        String subject = "New Account";
        String body = EmailFormatter.newAccount(to, username, newPassword);
        sendMail(to, subject, body);
    }
}