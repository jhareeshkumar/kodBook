package com.kodbook.otp.service.v1;

public interface OtpService {
    String generateOtp();

    void storeOtp(String username, String otp);

    boolean validateOtp(String username, String otp);

    void clearOtp(String username);

}
