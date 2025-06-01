package com.kodbook.web.repository;

import com.kodbook.userservice.entity.User;
import com.kodbook.web.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByUser(User user);
}
