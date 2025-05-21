package com.kodbook.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String dateOfBirth;
    private String gender;
    private String city;
    private String bio;
    private String college;
    private String linkedIn;
    private String gitHub;
}
