package com.kodbook.userservice.profile.service.impl;

import com.kodbook.userservice.profile.dto.UserProfileRegisterDto;
import com.kodbook.userservice.profile.entity.UserProfile;
import com.kodbook.userservice.profile.exception.custom.UserAlreadyExistsException;
import com.kodbook.userservice.profile.mapper.UserProfileMapper;
import com.kodbook.userservice.profile.repository.UserProfileRepository;
import com.kodbook.userservice.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileRegisterDto createUserProfile(UserProfileRegisterDto dto) {

        Optional<UserProfile> optional = userProfileRepository.findByUsername(dto.getUsername());
        if (optional.isPresent()) {
            throw new UserAlreadyExistsException("User profile already exists with username: " + dto.getUsername());
        }
        UserProfile savedEntity = userProfileRepository.save(userProfileMapper.toEntity(dto));
        return userProfileMapper.toDto(savedEntity);
    }
}
