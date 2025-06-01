package com.kodbook.authservice.dto;

import com.kodbook.authservice.enums.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AuthUserDto {
    private UUID publicId;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
