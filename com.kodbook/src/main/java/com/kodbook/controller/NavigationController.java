package com.kodbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class NavigationController {
    
    @GetMapping("/")
    public String index() {
	return "index";
    }

    @GetMapping("/openSignUp")
    public String openSignUp() {
	return "signUp";
    }

    @GetMapping("/openCreatePost")
    public String openCreatePost() {
	return "createPost";
    }
}
