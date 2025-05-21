package com.kodbook.user.mapper;

import com.kodbook.user.dto.UserDto;
import com.kodbook.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "userName", target = "username")
    @Mapping(source = "dob", target = "dateOfBirth")
    UserDto mapToDto(User user);

    User mapToEntity(UserDto userDto);

    List<UserDto> toUserDTOList(List<User> users);
}
