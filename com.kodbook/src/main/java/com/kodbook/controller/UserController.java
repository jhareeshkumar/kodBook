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
	System.out.println("testing" + userName + password);
	boolean loginStatus = userService.validateUser(userName, password);
	if (loginStatus) {
	    System.out.println("Login SuccessFull" + "" + loginStatus);

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
    public String getHome(Model model, HttpSession session) {

	String userName = (String) session.getAttribute("userName");
	if (userName == null) {
	    return "redirect:/";
	}

	model.addAttribute("posts", postService.getAllPosts());
	return "home";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute User user, @RequestParam MultipartFile photo, HttpSession session)
	    throws IOException {
	// TODO: process POST request
	// fetch user from session & Update
	String userName = (String) session.getAttribute("userName");
	if (userName == null) {
	    return "redirect:/";
	}
	
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

	if (userName == null) {
	    return "redirect:/";
	}

	
	System.out.println(userName);
	User user = userService.getUser(userName);
	model.addAttribute("user", user);
	List<Post> posts = user.getPosts();
	model.addAttribute("posts", posts);
	return "myProfile";
    }

    @PostMapping("/viewProfile")
    public String viewProfile(@RequestParam String userName, HttpSession session, Model model) {
	// TODO: process POST request
	User user = userService.getUser(userName);
	model.addAttribute("user", user);
	List<Post> posts = user.getPosts();
	model.addAttribute("posts", posts);

	return "myProfile";
    }

    @GetMapping("/openEditProfile")
    public String openEditProfile(HttpSession session) {

	String userName = (String) session.getAttribute("userName");
	if (userName == null) {
	    return "redirect:/";
	}
	return "editProfile";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
	String userName = (String) session.getAttribute("userName");
	if (userName == null) {
	    return "redirect:/";
	}
	// TODO: process POST request
	session.invalidate();
	System.out.println("logout success");
	return "redirect:/";
    }

}
