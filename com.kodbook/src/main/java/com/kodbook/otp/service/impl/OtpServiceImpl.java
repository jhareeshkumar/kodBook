package com.kodbook.otp.service.impl;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.kodbook.email.service.EmailService;
import com.kodbook.entity.User;
import com.kodbook.otp.service.OtpService;
import com.kodbook.user.v2.service.UserServiceV2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private static final int OTP_LENGTH = 6;
    private static final int OTP_MAX = 1000000;
    private final SecureRandom secureRandom = new SecureRandom();

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();

    @Override
    public String generateOtp() {
	return String.format("%0" + OTP_LENGTH + "d", secureRandom.nextInt(OTP_MAX));
    }

    @Override
    public void storeOtp(String username, String otp) {
	otpStorage.put(username, otp);
    }

    @Override
    public boolean validateOtp(String username, String otp) {
	return otp.equals(otpStorage.get(username));
    }

    @Override
    public void clearOtp(String username) {
	otpStorage.remove(username);
    }
}
