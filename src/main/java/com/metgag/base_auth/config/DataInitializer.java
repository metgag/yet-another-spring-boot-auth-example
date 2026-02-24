package com.metgag.base_auth.config;

import com.metgag.base_auth.entity.User;
import com.metgag.base_auth.entity.enums.Role;
import com.metgag.base_auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = IntStream.rangeClosed(1, 9)
                .mapToObj(i -> User.builder()
                        .username("user" + i)
                        .email("user" + i + "@example.com")
                        .password(passwordEncoder.encode("string"))
                        .role(Role.USER)
                        .build())
                .toList();
        userRepository.saveAll(users);

        userRepository.save(User.builder()
                .username("admin12")
                .email("admin121@example.com")
                .password(passwordEncoder.encode("admin121"))
                .role(Role.ADMIN)
                .build());
    }
}
