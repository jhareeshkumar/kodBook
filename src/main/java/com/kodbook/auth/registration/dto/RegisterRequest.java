package com.kodbook.auth.registration.dto;

import com.kodbook.auth.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}
