package com.BHServer.www.service;

import com.BHServer.www.domain.User;
import com.BHServer.www.dto.LoginRequest;

public interface UserService {
    User createUser(LoginRequest loginRequest);
}
