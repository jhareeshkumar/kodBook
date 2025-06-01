package com.kodbook.userservice.service.v2;

import com.kodbook.userservice.dto.PaginationRequest;
import com.kodbook.userservice.dto.UserDto;
import com.kodbook.userservice.entity.User;
import org.springframework.data.domain.Page;

public interface UserServiceV2 {
    User findByUsername(String username);

    void updateUser(User user);

    UserDto getUserById(Long id);

    Page<UserDto> getUsers(PaginationRequest paginationRequest);
}
