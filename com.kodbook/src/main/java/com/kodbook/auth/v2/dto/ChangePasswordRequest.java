package com.kodbook.auth.v2.dto;
 
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request to change password.")
public class ChangePasswordRequest {
    @NotBlank(message = "Old password is required.")
    private String oldPassword;
    
    @NotBlank(message = "New password is required.")
    @Size (min = 6, message = "New password must be at least 6 characters long.")
    private String newPassword;
    
    @NotBlank(message = "OTP is required")
    @Size(min = 6, max = 6, message = "OTP must be 6 characters long.")
    private String otp;
}
