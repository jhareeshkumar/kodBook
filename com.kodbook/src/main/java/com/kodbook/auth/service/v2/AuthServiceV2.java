package com.kodbook.auth.service.v2;

import com.kodbook.auth.api.v2.dto.ChangePasswordRequest;
import com.kodbook.auth.dto.AuthRequest;
import com.kodbook.auth.dto.AuthResponse;

public interface AuthServiceV2 {
    void changePassword(String username, ChangePasswordRequest request);

    void requestOtpForPasswordChange(String username);

    AuthResponse authenticate(AuthRequest request);
}
