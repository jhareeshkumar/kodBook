package com.kodbook.auth.v2.api;

import com.kodbook.auth.v2.dto.AuthRequest;
import com.kodbook.auth.v2.dto.AuthResponse;
import com.kodbook.dto.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication API V2", description = "Endpoints for authentication and authorization")
@RequestMapping("/api/auth/v2")
public interface AuthApi {
    @Operation(
            summary = "User Login",
            description = "Authenticates a user and returns an access token and refresh token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully logged in",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - Invalid credentials",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    )
            }
    )
    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request);
}
