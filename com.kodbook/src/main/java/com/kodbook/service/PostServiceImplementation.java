package com.kodbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entity.Post;
import com.kodbook.repository.PostRepository;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public void createPost(Post post) {
	// TODO Auto-generated method stub
	postRepository.save(post);
	System.out.println("Post saved successfully");
    }
}
