package com.kodbook.user.profile.service.impl;

import com.kodbook.user.profile.dto.UserProfileRegisterDto;
import com.kodbook.user.profile.entity.UserProfile;
import com.kodbook.user.profile.exception.custom.UserAlreadyExistsException;
import com.kodbook.user.profile.mapper.UserProfileMapper;
import com.kodbook.user.profile.repository.UserProfileRepository;
import com.kodbook.user.profile.service.UserProfileService;
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
