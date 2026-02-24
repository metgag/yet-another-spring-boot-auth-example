package com.metgag.base_auth;

import com.metgag.base_auth.security.JwtProperties;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
public class BaseAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseAuthApplication.class, args);
        log.info("\nAPI awaiting for requests...");
    }
}
