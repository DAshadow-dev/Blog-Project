package com.example.blog_back_end.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.blog_back_end.dto.request.PostCreationRequest;
import com.example.blog_back_end.dto.request.PostUpdateRequest;
import com.example.blog_back_end.dto.response.PostResponse;
import com.example.blog_back_end.mapper.PostMapper;
import com.example.blog_back_end.model.Post;
import com.example.blog_back_end.repository.PostRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;

    public List<PostResponse> getAllPost(){
        return postRepository.findAll().stream()
                    .map((post) -> postMapper.toPostResponse(post))
                    .collect(Collectors.toList());
    }

    public List<PostResponse> getPostsByUserName(String userName) {
        return postRepository.findAll()
                   .stream()
                   .filter(post -> post.getUser().getUsername().equals(userName))
                   .map(post -> postMapper.toPostResponse(post))
                   .collect(Collectors.toList());
    }

    public PostResponse createPost(PostCreationRequest request) {
        Post post = postMapper.toPost(request);
        Post savedPost = postRepository.save(post);
        return postMapper.toPostResponse(savedPost);
    }

    public PostResponse updatePost(String postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));
        postMapper.updatePostFromRequest(request, post);
        Post updatedPost = postRepository.save(post);
        return postMapper.toPostResponse(updatedPost);
    }

    public String deletePost(String postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found with ID: " + postId);
        }
        postRepository.deleteById(postId);
        return "Post has been deleted successfully at"+ LocalDateTime.now();
    }

}
