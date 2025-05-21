package com.kodbook.user.service.v2;

import com.kodbook.user.dto.UserDto;
import com.kodbook.user.entity.User;
import org.springframework.data.domain.Page;

public interface UserServiceV2 {
    User findByUsername(String username);

    void updateUser(User user);

    UserDto getUserById(Long id);

    Page<UserDto> getUsers(int pageNumber, int pageSize, String sortDirection, String[] sortBy);
}
