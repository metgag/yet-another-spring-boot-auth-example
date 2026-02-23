package com.metgag.base_auth.controller;

import com.metgag.base_auth.dto.request.LoginRequest;
import com.metgag.base_auth.dto.request.RegisterRequest;
import com.metgag.base_auth.dto.response.ApiResponse;
import com.metgag.base_auth.dto.response.LoginResponse;
import com.metgag.base_auth.dto.response.UserResponse;
import com.metgag.base_auth.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse<UserResponse>> handleRegister(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse responseDto = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User registered successfully", responseDto));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<LoginResponse>> handleLogin(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse responseDto = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("User logged in successfully", responseDto));
    }
}
