package com.kodbook.auth.v2.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodbook.auth.v2.dto.ChangePasswordRequest;
import com.kodbook.auth.v2.service.AuthServiceV2;
import com.kodbook.email.service.EmailService;
import com.kodbook.entity.User;
import com.kodbook.exception.custom.IncorrectPasswordException;
import com.kodbook.exception.custom.InvalidOtpException;
import com.kodbook.otp.service.OtpService;
import com.kodbook.user.v2.service.UserServiceV2;

import lombok.RequiredArgsConstructor;

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
	emailService.sendEmail(to, subject, content);
    }
}
