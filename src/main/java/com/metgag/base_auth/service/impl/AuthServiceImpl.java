package com.metgag.base_auth.service.impl;

import com.metgag.base_auth.dto.request.LoginRequest;
import com.metgag.base_auth.dto.request.RegisterRequest;
import com.metgag.base_auth.dto.response.LoginResponse;
import com.metgag.base_auth.dto.response.UserResponse;
import com.metgag.base_auth.entity.User;
import com.metgag.base_auth.entity.enums.Role;
import com.metgag.base_auth.exception.ApiException;
import com.metgag.base_auth.repository.UserRepository;
import com.metgag.base_auth.security.JwtService;
import com.metgag.base_auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
        }

        User newUser = userRepository.save(User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build());
        return UserResponse.fromEntity(newUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(
                        () -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername());
        return LoginResponse.builder()
                .token(token)
                .user(UserResponse.fromEntity(user))
                .build();
    }
}
