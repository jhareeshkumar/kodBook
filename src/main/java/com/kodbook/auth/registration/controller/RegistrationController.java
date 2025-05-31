package com.kodbook.auth.registration.controller;

import com.kodbook.auth.dto.AuthUserDto;
import com.kodbook.auth.registration.api.RegistrationApi;
import com.kodbook.auth.registration.dto.RegisterRequest;
import com.kodbook.auth.registration.service.RegistrationService;
import com.kodbook.user.profile.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController implements RegistrationApi {

    private final RegistrationService registrationService;

    @Override
    public ResponseEntity<ApiResponse<AuthUserDto>> register(RegisterRequest request) {
        ApiResponse<AuthUserDto> authUserDtoApiResponse = new ApiResponse<>();
        authUserDtoApiResponse.setSuccess(true);
        authUserDtoApiResponse.setMessage("User registered successfully");
        authUserDtoApiResponse.setData(registrationService.register(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(authUserDtoApiResponse);
    }
}
