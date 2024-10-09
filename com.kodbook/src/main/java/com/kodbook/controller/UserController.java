package com.kodbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entity.Post;
import com.kodbook.entity.User;
import com.kodbook.service.PostService;
import com.kodbook.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private PostService postService;

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute User user) {
	// TODO: process POST request
	boolean signUpStatus = userService.userExists(user.getUserName(), user.getEmail());
	if (signUpStatus == false) {
	    userService.addUser(user);
	    System.out.println("User Added Successfully");
	}
	System.out.println("User already Exists with same details");
	return "index";
    }
    @PostMapping("/login")
    public String login(@RequestParam String userNameOrEmail, @RequestParam String password,Model model) {
        //TODO: process POST request
        boolean loginStatus = userService.validateUser(userNameOrEmail,password);
        if (loginStatus) {
            System.out.println("Login SuccessFull");
            List<Post> posts = postService.getAllPosts();
            model.addAttribute("posts",posts);
	    return "home";
	}
        System.out.println("Invalid Credentials Please Login");
        return "index";
    }
    
    @GetMapping("/home")
    public String getHome(Model model) {
         model.addAttribute("posts",postService.getAllPosts());
         return "home";
    }
    
    
}
