package com.kodbook.web.service;

import com.kodbook.user.entity.User;
import com.kodbook.web.entity.Like;
import com.kodbook.web.entity.Post;
import com.kodbook.web.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public boolean toggleLike(Long id, String userName) {
        // TODO Auto-generated method stub
        User user = userService.getUser(userName);
        Post post = postService.getPost(id);
        Optional<Like> optional = likeRepository.findByUserAndPost(user, post);
        if (optional.isPresent()) {
            //delete like
            likeRepository.delete(optional.get());
            //decrease like count in post
            post.setLikes(post.getLikes() - 1);
            //update post
            postService.updatePost(post);
            return false;
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);

            //save like
            likeRepository.save(like);

            //increase like count
            post.setLikes(post.getLikes() + 1);
            //update post
            postService.updatePost(post);
            return true;
        }

    }


}
