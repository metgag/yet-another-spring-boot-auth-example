package com.metgag.base_auth.service;

import com.metgag.base_auth.dto.response.UserResponse;

public interface UserService {

    UserResponse getCurrentUserDetails(String username);
}
