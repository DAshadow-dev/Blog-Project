package com.example.blog_back_end.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog_back_end.dto.request.UserCreationRequest;
import com.example.blog_back_end.dto.request.UserUpdateRequest;
import com.example.blog_back_end.model.User;
import com.example.blog_back_end.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUser(String userId){
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User createUser(UserCreationRequest userCreationRequest){
        if(userRepository.existsByUsername(userCreationRequest.getUsername()))
            throw new RuntimeException("User already exists");

        return userRepository.save(User.builder()
                .username(userCreationRequest.getUsername())
                .password(userCreationRequest.getPassword())
                .email(userCreationRequest.getEmail())
                .role(userCreationRequest.getRole())
                .build());
    }

    public User updateUser(String userId, UserUpdateRequest userUpdateRequest){
        User user = getUser(userId);
        return userRepository.save(User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build());
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
