package com.kodbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entity.Post;
import com.kodbook.entity.User;
import com.kodbook.service.LikeService;
import com.kodbook.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;

    @PostMapping("/likePost")
    public String likePost(@RequestParam Long id, HttpSession session, Model model) {
	// TODO: process POST request
	String userName = (String) session.getAttribute("userName");
	if (userName == null) {
	    return "redirect:/";
	}
	boolean isLiked = likeService.toggleLike(id, userName);
	model.addAttribute("isLiked", isLiked);
	User user = userService.getUser(userName);
	model.addAttribute("user", user);
	List<Post> posts = user.getPosts();
	model.addAttribute("posts", posts);
	return "myProfile";
    }

}
