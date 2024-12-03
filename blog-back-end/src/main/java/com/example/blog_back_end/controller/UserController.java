package com.example.blog_back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_back_end.dto.request.UserCreationRequest;
import com.example.blog_back_end.dto.request.UserUpdateRequest;
import com.example.blog_back_end.dto.response.ApiResponse;
import com.example.blog_back_end.dto.response.UserResponse;
import com.example.blog_back_end.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }
    
    @PostMapping
    public ApiResponse<UserResponse> creatUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationRequest));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updatUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, userUpdateRequest));
        return apiResponse;
    }
     @DeleteMapping("/{userId}")
    public ApiResponse<?> deleteUser(@PathVariable String userId){
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(userService.deleteUser(userId));
        return apiResponse;
    }
}
