package com.kodbook.userservice.profile.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserProfileRegisterDto {
    private UUID publicId;
    private String username;
}
