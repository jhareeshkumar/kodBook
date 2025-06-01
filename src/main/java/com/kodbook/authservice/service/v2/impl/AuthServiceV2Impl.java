package com.kodbook.authservice.service.v2.impl;

import com.kodbook.authservice.api.v2.dto.ChangePasswordRequest;
import com.kodbook.authservice.dto.AuthRequest;
import com.kodbook.authservice.dto.AuthResponse;
import com.kodbook.authservice.exception.EmailSendFailureException;
import com.kodbook.authservice.exception.IncorrectPasswordException;
import com.kodbook.authservice.exception.InvalidOtpException;
import com.kodbook.authservice.service.v2.AuthServiceV2;
import com.kodbook.emailservice.service.v1.EmailService;
import com.kodbook.otpservice.service.v1.OtpService;
import com.kodbook.userservice.entity.User;
import com.kodbook.userservice.service.v2.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceV2Impl implements AuthServiceV2 {

    private final UserServiceV2 userService;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public void changePassword(String username, ChangePasswordRequest request) {
        User user = userService.findByUsername(username);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Old password is incorrect");
        }

        if (!otpService.validateOtp(username, request.getOtp())) {
            throw new InvalidOtpException("Invalid or expired OTP");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userService.updateUser(user);

        otpService.clearOtp(username);
    }

    @Override
    public void requestOtpForPasswordChange(String username) {
        User user = userService.findByUsername(username);
        String otp = otpService.generateOtp();
        otpService.storeOtp(username, otp);

        String subject = "OTP for password change";
        String content = "Your OTP is: " + otp;
        String to = user.getEmail();
        boolean isEmailSent = emailService.sendEmail(to, subject, content);
        if (!isEmailSent) {
            throw new EmailSendFailureException("Failed to send email");
        }
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        userService.findByUsername(request.getUsername());
        return new AuthResponse("access-token", "refresh-token", "Bearer", 3600L, "Successfully logged in");
    }
}
