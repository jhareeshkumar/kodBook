package com.kodbook.user.mapper;

import com.kodbook.user.dto.UserDto;
import com.kodbook.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);

    User mapToEntity(UserDto userDto);

    List<UserDto> toUserDTOList(List<User> users);
}
