package com.kodbook.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(name = "AuthRequest", description = "Request body for user authentication.")
public class AuthRequest {
    @Schema(description = "Username of the user.", example = "john", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @Schema(description = "Password of the user.", example = "P@ssw0rd", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
