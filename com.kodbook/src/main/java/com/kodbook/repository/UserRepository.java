package com.kodbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByEmail(String email);

    Optional<User> findByUserNameAndPassword(String userName, String password);

    Optional<User> findByUserNameOrEmail(String userName, String email);

}