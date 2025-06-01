package com.kodbook.authservice.registration.service;

import com.kodbook.authservice.dto.AuthUserDto;
import com.kodbook.authservice.registration.dto.RegisterRequest;

public interface RegistrationService {
    AuthUserDto register(RegisterRequest request);
}
