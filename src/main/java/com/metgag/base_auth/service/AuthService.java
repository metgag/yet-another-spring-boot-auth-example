package com.metgag.base_auth.service;

import com.metgag.base_auth.dto.request.LoginRequest;
import com.metgag.base_auth.dto.request.RegisterRequest;
import com.metgag.base_auth.dto.response.LoginResponse;
import com.metgag.base_auth.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
