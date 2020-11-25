package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable  @RequestBody  Long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
}
