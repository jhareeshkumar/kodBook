package com.kodbook.web.service;

import com.kodbook.user.entity.User;
import com.kodbook.web.entity.Post;
import com.kodbook.web.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        // TODO Auto-generated method stub
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostByUser(User user) {
        return postRepository.findPostByUser(user);
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

    @Override
    public void addComment(Long id, String comment) {
        // TODO Auto-generated method stub
        Optional<Post> optional = postRepository.findById(id);
        if (optional.isPresent()) {
            Post post = optional.get();
            List<String> comments = post.getComments();
            if (comments == null) {
                comments = new ArrayList<>();
            }
            comments.add(comment);
            post.setComments(comments);
            postRepository.save(post);
            System.out.println("Added comment successfully");
        }

    }
}
