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
    @Transactional
    public void createPost(Post post) {
        postRepository.save(post);
        System.out.println("Post saved successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPostByUser(User user) {
        return postRepository.findPostByUser(user);
    }

    @Override
    public void incrementLike(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setLikes(post.getLikes() + 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void addComment(Long id, String comment) {
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
