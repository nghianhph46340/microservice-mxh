package com.example.postsservice.command.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {
    @GetMapping("/posts")
    public String getPosts() {
        return "Posts";
    }
}
