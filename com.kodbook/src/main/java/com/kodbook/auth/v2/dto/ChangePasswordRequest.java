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
    @Schema(description = "Current password of the user", format = "password",example = "P@ssw0rd")
    @NotBlank(message = "Old password is required.")
    private String oldPassword;

    @Schema(description = "New password to be set", format = "password",example = "newP@ssw0rd")
    @NotBlank(message = "New password is required.")
    @Size (min = 6, max = 20 ,message = "New password must be at least 6 characters long.")
    private String newPassword;

    @Schema(description = "OTP for password change", format = "OTP",example = "123456")
    @NotBlank(message = "OTP is required")
    @Size(min = 6, max = 6, message = "OTP must be 6 digits")
    private String otp;
}
