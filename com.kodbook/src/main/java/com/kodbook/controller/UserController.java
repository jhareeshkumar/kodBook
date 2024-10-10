package com.kodbook.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entity.Post;
import com.kodbook.entity.User;
import com.kodbook.service.PostService;
import com.kodbook.service.UserService;

import jakarta.servlet.http.HttpSession;

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
    public String login(@RequestParam String userName, @RequestParam String password, Model model,
	    HttpSession session) {
	// TODO: process POST request
	boolean loginStatus = userService.validateUser(userName, password);
	if (loginStatus) {
	    System.out.println("Login SuccessFull");

	    session.setAttribute("userName", userName);
	    model.addAttribute("session", session);

	    List<Post> posts = postService.getAllPosts();
	    model.addAttribute("posts", posts);
	    return "home";
	}
	System.out.println("Invalid Credentials Please Login");
	return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
	model.addAttribute("posts", postService.getAllPosts());
	return "home";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute User user, @RequestParam MultipartFile photo, HttpSession session)
	    throws IOException {
	// TODO: process POST request

	// fetch user from session & Update
	String userName = (String) session.getAttribute("userName");

	User dbUser = userService.getUser(userName);
	dbUser.setDob(user.getDob());
	dbUser.setCity(user.getCity());
	dbUser.setBio(user.getBio());
	dbUser.setCollege(user.getCollege());
	dbUser.setGender(user.getGender());
	dbUser.setLinkedIn(user.getLinkedIn());
	dbUser.setGitHub(user.getGitHub());
	dbUser.setProfilePic(photo.getBytes());

	userService.updateUser(dbUser);

	return "redirect:/home";
    }

    @GetMapping("/openMyProfile")
    public String openMyProfile(HttpSession session, Model model) {
	String userName = (String) session.getAttribute("userName");
	User user = userService.getUser(userName);
	model.addAttribute("user", user);
	return "myProfile";
    }

}
