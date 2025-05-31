package com.kodbook.auth.registration.service;

import com.kodbook.auth.dto.AuthUserDto;
import com.kodbook.auth.registration.dto.RegisterRequest;

public interface RegistrationService {
    AuthUserDto register(RegisterRequest request);
}
