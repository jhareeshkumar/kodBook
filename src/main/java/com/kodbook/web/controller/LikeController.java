package com.kodbook.web.controller;

import com.kodbook.userservice.entity.User;
import com.kodbook.web.entity.Post;
import com.kodbook.web.service.LikeService;
import com.kodbook.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/web/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;

    @PostMapping("/likePost")
    public String likePost(@RequestParam Long id, Authentication authentication, Model model) {
        // TODO: process POST request
        String username = authentication.getName();
        boolean isLiked = likeService.toggleLike(id, username);
        model.addAttribute("isLiked", isLiked);
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        List<Post> posts = user.getPosts();
        model.addAttribute("posts", posts);
        return "myProfile";
    }

}
