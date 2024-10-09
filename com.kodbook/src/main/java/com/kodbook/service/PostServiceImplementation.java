package com.kodbook.service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Post> getAllPosts() {
	// TODO Auto-generated method stub
	return postRepository.findAll();
    }

    @Override
    public void incrementLike(Long id) {
	// TODO Auto-generated method stub
	Optional<Post> postOptional = postRepository.findById(id);
	if (postOptional.isPresent()) {
	    Post post = postOptional.get();
	    post.setLikes(post.getLikes() + 1);
	}
    }

    @Override
    public Post getPost(Long id) {
	// TODO Auto-generated method stub
	return postRepository.findById(id).get();
    }

    @Override
    public void updatePost(Post post) {
	// TODO Auto-generated method stub
	postRepository.save(post);
    }
}
