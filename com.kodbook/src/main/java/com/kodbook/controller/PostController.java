package com.kodbook.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entity.Post;
import com.kodbook.service.PostService;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/createPost")
    public String createPost(@RequestParam String caption, @RequestParam MultipartFile photo) throws IOException {
	// TODO: process POST request
	Post post = new Post();
	post.setCaption(caption);
	post.setPhoto(photo.getBytes());
	postService.createPost(post);
	return "redirect:/home";
    }

    @GetMapping("/getAllPosts")
    public String getAllPosts(Model model) {
	List<Post> posts = postService.getAllPosts();
	model.addAttribute("posts", posts);
	return "home";
    }
    
    @PostMapping("/likePost")
    public String postMethodName(@RequestParam Long id,Model model) {
	//TODO: process POST request
	Post post = postService.getPost(id);
	postService.incrementLike(id);
	postService.updatePost(post);
	model.addAttribute("posts",postService.getAllPosts());
	return "redirect:/home";
    }

}
