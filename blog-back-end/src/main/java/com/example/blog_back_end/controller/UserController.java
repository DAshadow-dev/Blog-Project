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
import com.example.blog_back_end.model.User;
import com.example.blog_back_end.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
    
    @PostMapping
    public User creatUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        return userService.createUser(userCreationRequest);
    }

    @PutMapping("/{userId}")
    public User updatUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userId, userUpdateRequest);
    }
     @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User deleted successfully";
    }
}
