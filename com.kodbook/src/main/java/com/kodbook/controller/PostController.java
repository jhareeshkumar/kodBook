package com.kodbook.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entity.Post;
import com.kodbook.service.PostService;


@Controller
public class PostController {
    @Autowired
    private PostService postService;
    
    @PostMapping("/createPost")
    public String createPost(@RequestParam String caption,@RequestParam MultipartFile photo) throws IOException {
        //TODO: process POST request
	Post post = new Post();
        post.setCaption(caption);
        post.setPhoto(photo.getBytes());
        postService.createPost(post);
        return "redirect:/home";
    }
    
}
