package com.kodbook.userservice.api;

import com.kodbook.userservice.dto.PaginationRequest;
import com.kodbook.userservice.dto.UserDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This interface defines the API for user-related operations.
 * It includes methods for retrieving user information
 * and paginated lists of users.
 * The API is versioned as v2.
 */
@RequestMapping("/api/v2/users")
public interface UserApi {

    /**
     * Returns a user by their ID.
     *
     * @param id the ID of the user to return
     * @return the user with the given ID, or a 404 response if no such user exists
     */
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id);

    /**
     * Retrieves a page of user Dto objects based on the PaginationRequest passed in
     *
     * @param paginationRequest the pagination request object
     * @return a ResponseEntity containing a page of UserDto objects
     */
    @GetMapping
    ResponseEntity<Page<UserDto>> getUsers(@ParameterObject PaginationRequest paginationRequest);
}
