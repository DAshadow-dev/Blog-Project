package com.example.blog_back_end.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.blog_back_end.dto.request.UserCreationRequest;
import com.example.blog_back_end.dto.request.UserUpdateRequest;
import com.example.blog_back_end.dto.response.UserResponse;
import com.example.blog_back_end.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

