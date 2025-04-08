package com.kodbook.web.controller;

import com.kodbook.user.entity.User;
import com.kodbook.web.entity.Post;
import com.kodbook.web.service.PostService;
import com.kodbook.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/web")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/login")
    public String login(Model model, String error) {
        model.addAttribute("error", error != null);
        return "index";
    }

    @GetMapping("/openSignUp")
    public String openSignUp() {
        return "signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user, Model model) {
        // TODO: process POST request
        boolean signUpStatus = userService.userExists(user.getUserName(), user.getEmail());
        if (signUpStatus == false) {
            userService.addUser(user);
            System.out.println("User Added Successfully");
            model.addAttribute("success", "signUp SuccessFull. Please Login.");
            return "index";
        }
        model.addAttribute("error", "User already Exists with same details. Please try to login.");
        System.out.println("User already Exists with same details.");
        return "signUp";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password, Model model,
//	    HttpSession session) {
//	// TODO: process POST request
//	System.out.println("testing" + username + password);
//	boolean loginStatus = userService.validateUser(username, password);
//	if (loginStatus) {
//	    System.out.println("Login SuccessFull" + "" + loginStatus);
//
//	    session.setAttribute("userName", username);
//	    model.addAttribute("session", session);
//
//	    
//	    // Set authentication in Spring Security context
//	        UsernamePasswordAuthenticationToken auth = 
//	            new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("USER")));
//	        SecurityContextHolder.getContext().setAuthentication(auth);
//	    
//	    
//	    List<Post> posts = postService.getAllPosts();
//	    model.addAttribute("posts", posts);
//	    return "home";
//	}
//	System.out.println("Invalid Credentials Please Login");
//	return "index";
//    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "home";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute User user, @RequestParam MultipartFile photo, Authentication authentication)
            throws IOException {
        String username = authentication.getName();
        User dbUser = userService.getUser(username);
        dbUser.setDob(user.getDob());
        dbUser.setCity(user.getCity());
        dbUser.setBio(user.getBio());
        dbUser.setCollege(user.getCollege());
        dbUser.setGender(user.getGender());
        dbUser.setLinkedIn(user.getLinkedIn());
        dbUser.setGitHub(user.getGitHub());
        dbUser.setProfilePic(photo.getBytes());

        userService.updateUser(dbUser);

        return "redirect:/web/home";
    }

    @GetMapping("/openMyProfile")
    public String openMyProfile(Model model, Authentication authentication) {

        String username = authentication.getName();

        System.out.println(username);
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        List<Post> posts = postService.getAllPostByUser(user);
        model.addAttribute("posts", posts);
        return "myProfile";
    }

    @PostMapping("/viewProfile")
    public String viewProfile(@RequestParam String userName, Model model) {
        // TODO: process POST request
        User user = userService.getUser(userName);
        model.addAttribute("user", user);
        List<Post> posts = postService.getAllPostByUser(user);
        model.addAttribute("posts", posts);

        return "myProfile";
    }

    @GetMapping("/openEditProfile")
    public String openEditProfile() {
        return "editProfile";
    }

//    @GetMapping(value = "/logout")
//    public String logout(HttpSession session) {
//	String userName = (String) session.getAttribute("userName");
//	if (userName == null) {
//	    return "redirect:/";
//	}
//	// TODO: process POST request
//	session.invalidate();
//	System.out.println("logout success");
//	return "redirect:/";
//    }

}
