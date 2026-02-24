package com.metgag.base_auth.controller;

import com.metgag.base_auth.dto.response.ApiResponse;
import com.metgag.base_auth.dto.response.UserResponse;
import com.metgag.base_auth.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/me")
    public ResponseEntity<ApiResponse<UserResponse>> me(
            @AuthenticationPrincipal(expression = "username") String username) {

        UserResponse currentUser = userService.getCurrentUserDetails(username);
        return ResponseEntity.ok(
                ApiResponse.success("User details retrieved successfully", currentUser));
    }
}
