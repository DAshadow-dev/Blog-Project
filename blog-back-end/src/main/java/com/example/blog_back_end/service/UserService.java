package com.example.blog_back_end.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog_back_end.Enum.ErrorCode;
import com.example.blog_back_end.dto.request.UserCreationRequest;
import com.example.blog_back_end.dto.request.UserUpdateRequest;
import com.example.blog_back_end.dto.response.UserResponse;
import com.example.blog_back_end.exception.UserException;
import com.example.blog_back_end.mapper.UserMapper;
import com.example.blog_back_end.model.User;
import com.example.blog_back_end.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                    .map((user) -> userMapper.toUserResponse(user))
                    .collect(Collectors.toList());
    }
    public UserResponse getUser(String userId){

        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new UserException(ErrorCode.USER_NOT_EXISTED)));
    }
    public UserResponse createUser(UserCreationRequest userCreationRequest){
        if(userRepository.existsByUsername(userCreationRequest.getUsername()))
            throw new UserException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(userCreationRequest);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, userUpdateRequest);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
