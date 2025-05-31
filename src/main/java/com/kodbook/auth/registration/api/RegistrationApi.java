package com.kodbook.auth.registration.api;


import com.kodbook.auth.dto.AuthUserDto;
import com.kodbook.auth.registration.dto.RegisterRequest;
import com.kodbook.user.profile.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v3/auth")
public interface RegistrationApi {

    @PostMapping("/register")
    ResponseEntity<ApiResponse<AuthUserDto>> register(@RequestBody RegisterRequest request);
}
