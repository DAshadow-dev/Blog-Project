package com.example.blog_back_end.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_back_end.dto.request.ApiResponse;
import com.example.blog_back_end.dto.request.AuthenticationRequest;
import com.example.blog_back_end.dto.response.AuthenticationResponse;
import com.example.blog_back_end.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result = authenticationService.authenticate(request);
        
        return ApiResponse.<AuthenticationResponse>builder()
                    .result(AuthenticationResponse.builder()
                        .authenticated(result)
                        .build())
                    .build();
    }
    
}
