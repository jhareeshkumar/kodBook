package com.kodbook.authservice.registration.service.impl;

import com.kodbook.authservice.dto.AuthUserDto;
import com.kodbook.authservice.entity.AuthUser;
import com.kodbook.authservice.mapper.AuthUserMapper;
import com.kodbook.authservice.registration.client.UserServiceClient;
import com.kodbook.authservice.registration.dto.RegisterRequest;
import com.kodbook.authservice.registration.service.RegistrationService;
import com.kodbook.authservice.repository.AuthUserRepository;
import com.kodbook.userservice.profile.exception.custom.UserAlreadyExistsException;
import com.kodbook.userservice.profile.service.UserProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserServiceClient userServiceClient;
    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileService userProfileService;

    @Transactional
    @Override
    public AuthUserDto register(RegisterRequest registerRequest) {

        Optional<AuthUser> byUsername = authUserRepository.findByUsername(registerRequest.getUsername());
        Optional<AuthUser> byEmail = authUserRepository.findByEmail(registerRequest.getEmail());
        if (byUsername.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + registerRequest.getUsername());
        }
        if (byEmail.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + registerRequest.getEmail());
        }
        AuthUser authUser = authUserMapper.toAuthUserEntity(registerRequest);
        authUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        AuthUser savedAuthUser = authUserRepository.save(authUser);

        //call user service to create user profile
        userServiceClient.createUserProfile(authUserMapper.toUserProfileRegisterDto(savedAuthUser));
        return authUserMapper.toAuthUserDto(savedAuthUser);
    }
}
