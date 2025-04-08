package com.kodbook.web.service;

import com.kodbook.web.entity.Post;

import java.util.List;

public interface PostService {

    void createPost(Post post);

    List<Post> getAllPosts();

    Post getPost(Long id);

    void incrementLike(Long id);

    void updatePost(Post post);

    void addComment(Long id, String comment);
}
