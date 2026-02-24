package com.metgag.base_auth.service.impl;

import com.metgag.base_auth.dto.response.UserResponse;
import com.metgag.base_auth.entity.User;
import com.metgag.base_auth.repository.UserRepository;
import com.metgag.base_auth.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getCurrentUserDetails(String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return UserResponse.fromEntity(user);
    }
}
