package com.kodbook.authservice.registration.controller;

import com.kodbook.authservice.dto.AuthUserDto;
import com.kodbook.authservice.registration.api.RegistrationApi;
import com.kodbook.authservice.registration.dto.RegisterRequest;
import com.kodbook.authservice.registration.service.RegistrationService;
import com.kodbook.userservice.profile.dto.ApiResponse;
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
