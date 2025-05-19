/**
 *
 */
package com.kodbook.web.repository;

import com.kodbook.user.entity.User;
import com.kodbook.web.entity.Like;
import com.kodbook.web.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 *
 */
public interface LikeRepository extends JpaRepository<Like, UUID> {

    Optional<Like> findByUserAndPost(User user, Post post);
}
