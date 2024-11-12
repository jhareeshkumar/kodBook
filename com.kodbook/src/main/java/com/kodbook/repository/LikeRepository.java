/**
 * 
 */
package com.kodbook.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entity.Like;
import com.kodbook.entity.Post;
import com.kodbook.entity.User;

/**
 * 
 */
public interface LikeRepository extends JpaRepository<Like, UUID> {

    Optional<Like> findByUserAndPost(User user, Post post);
}
