package com.kodbook.authservice.service.v2;

import com.kodbook.authservice.api.v2.dto.ChangePasswordRequest;
import com.kodbook.authservice.dto.AuthRequest;
import com.kodbook.authservice.dto.AuthResponse;

public interface AuthServiceV2 {
    void changePassword(String username, ChangePasswordRequest request);

    void requestOtpForPasswordChange(String username);

    AuthResponse authenticate(AuthRequest request);
}
