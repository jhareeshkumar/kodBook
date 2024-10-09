package com.kodbook.service;

import java.util.List;

import com.kodbook.entity.Post;

public interface PostService {

    void createPost(Post post);

    List<Post> getAllPosts();
    
    Post getPost(Long id);
    
    void incrementLike(Long id);
    
    void updatePost(Post post);

    void addComment(Long id, String comment);
}
