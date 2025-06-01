package com.kodbook.web.service;

import com.kodbook.userservice.entity.User;
import com.kodbook.web.entity.Post;

import java.util.List;

public interface PostService {

    void createPost(Post post);

    List<Post> getAllPosts();

    List<Post> getAllPostByUser(User user);

    Post getPost(Long id);

    void incrementLike(Long id);

    void updatePost(Post post);

    void addComment(Long id, String comment);
}
