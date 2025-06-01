package com.kodbook.authservice.registration.dto;

import com.kodbook.authservice.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}
