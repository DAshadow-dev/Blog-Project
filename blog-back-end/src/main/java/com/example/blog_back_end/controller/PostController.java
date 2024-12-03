package com.example.blog_back_end.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_back_end.dto.request.PostCreationRequest;
import com.example.blog_back_end.dto.request.PostUpdateRequest;
import com.example.blog_back_end.dto.response.ApiResponse;
import com.example.blog_back_end.dto.response.PostResponse;
import com.example.blog_back_end.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    PostService postService;

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() {
        return ApiResponse.<List<PostResponse>>builder()
            .result(postService.getAllPost())
            .build();
    }

    @PostMapping
    public ApiResponse<PostResponse> createPost(@RequestBody PostCreationRequest post) {
        return ApiResponse.<PostResponse>builder()
            .result(postService.createPost(post))
            .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PostResponse> updatePost(@PathVariable String id, @RequestBody PostUpdateRequest post) {
        return ApiResponse.<PostResponse>builder()
            .result(postService.updatePost(id, post))
            .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deletePost(@PathVariable String id) {
        return ApiResponse.builder()
            .message(postService.deletePost(id))
            .build();
    }
}

