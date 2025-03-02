package com.kodbook.auth.v2.service;

import com.kodbook.auth.v2.dto.ChangePasswordRequest;

public interface AuthServiceV2 {
    void changePassword(String username, ChangePasswordRequest request);
    void requestOtpForPasswordChange(String username);
}
