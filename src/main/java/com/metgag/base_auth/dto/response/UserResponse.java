package com.metgag.base_auth.dto.response;

import com.metgag.base_auth.entity.User;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record UserResponse(UUID id, String username, String email, String role, Instant createdAt) {

    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
