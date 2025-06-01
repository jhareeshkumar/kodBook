package com.kodbook.authservice.registration.api;


import com.kodbook.authservice.dto.AuthUserDto;
import com.kodbook.authservice.registration.dto.RegisterRequest;
import com.kodbook.userservice.profile.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Registration API for user registration.
 * This interface defines the endpoint for registering a new user.
 */
@RequestMapping("/api/v3/auth")
public interface RegistrationApi {

    /**
     * Registers a new user.
     *
     * @param registerRequest The registration registerRequest containing user details.
     * @return A response entity containing the registered user details.
     */
    @PostMapping("/register")
    ResponseEntity<ApiResponse<AuthUserDto>> register(@RequestBody RegisterRequest registerRequest);
}
