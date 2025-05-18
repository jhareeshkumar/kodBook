package com.kodbook.auth.controller.v2;

import com.kodbook.auth.api.v2.AuthApi;
import com.kodbook.auth.api.v2.dto.ChangePasswordRequest;
import com.kodbook.auth.dto.AuthRequest;
import com.kodbook.auth.dto.AuthResponse;
import com.kodbook.auth.service.v2.AuthServiceV2;
import com.kodbook.dto.response.ErrorResponse;
import com.kodbook.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthControllerV2 implements AuthApi {

    private final AuthServiceV2 authService;

    @Autowired
    public AuthControllerV2(AuthServiceV2 authService) {
        this.authService = authService;
    }

    @PostMapping("/password/request-otp")
    @Operation(summary = "Request OTP for Password Update", description = "Allows logged-in users to request OTP for password change")
    @ApiResponse(responseCode = "400", description = "Invalid Username", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<SuccessResponse> requestOtpForPasswordChange(
            @AuthenticationPrincipal UserDetails userDetails) {
        authService.requestOtpForPasswordChange(userDetails.getUsername());
        return ResponseEntity.ok(new SuccessResponse("OTP sent successfully to your registered email"));
    }

    @PutMapping("/password/change")
    @Operation(summary = "Update password", description = "Allows authenticated users to update their password after OTP verification")
    public ResponseEntity<SuccessResponse> updatePassword(@Valid @RequestBody ChangePasswordRequest request,
                                                          @AuthenticationPrincipal UserDetails userDetails) {
        authService.changePassword(userDetails.getUsername(), request);
        return ResponseEntity.ok(new SuccessResponse("Password changed successfully"));
    }

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
