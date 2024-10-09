package com.kodbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
