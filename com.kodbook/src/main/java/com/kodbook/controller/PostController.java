package com.kodbook.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.kodbook.entity.User;
import com.kodbook.repository.UserRepository;
import com.kodbook.service.PostService;
import com.kodbook.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/openCreatePost")
    public String openCreatePost(HttpSession session) {
	
	String userName = (String) session.getAttribute("userName");
	if (userName == null) {
	    return "redirect:/";
	}
	
	return "createPost";
    }
    
    @PostMapping("/createPost")
    public String createPost(@RequestParam String caption, @RequestParam MultipartFile photo,HttpSession session) throws IOException {
	// TODO: process POST request
	Post post = new Post();
	post.setCaption(caption);
	post.setPhoto(photo.getBytes());
	
	String userName = (String) session.getAttribute("userName");
	User user = userService.getUser(userName);
	//mapping user to post
	post.setUser(user);
	postService.createPost(post);
	//update user with posts to map posts
	List<Post> posts = user.getPosts();
	if (posts==null) {
	    posts = new ArrayList<Post>();
	}
	posts.add(post);//add post to list
	user.setPosts(posts);//set posts to user
	userService.updateUser(user);//update
	return "redirect:/home";
    }

    @GetMapping("/getAllPosts")
    public String getAllPosts(Model model,HttpSession session) {
	
	String userName = (String) session.getAttribute("userName");
	if (userName == null) {
	    return "redirect:/";
	}
	
	List<Post> posts = postService.getAllPosts();
	model.addAttribute("posts", posts);
	return "home";
    }

    @PostMapping("/likePost")
    public String likePost(@RequestParam Long id, Model model) {
	// TODO: process POST request
	Post post = postService.getPost(id);
	postService.incrementLike(id);
	postService.updatePost(post);
	model.addAttribute("posts", postService.getAllPosts());
	return "redirect:/home";
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam Long id, String comment, Model model) {
	// TODO: process POST request
	postService.addComment(id, comment);
	model.addAttribute("posts", postService.getAllPosts());
	return "redirect:/home";
    }

}
