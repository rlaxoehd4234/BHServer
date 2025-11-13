package com.BHServer.www.service.impl;

import com.BHServer.www.domain.User;
import com.BHServer.www.dto.LoginRequest;
import com.BHServer.www.repository.UserRepository;
import com.BHServer.www.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceV1 implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User createUser(LoginRequest loginRequest) {

        User loginUser = User.of(loginRequest.getUsername(), passwordEncoder.encode(loginRequest.getPassword()));
        log.info(loginUser.getUsername());
        log.info(passwordEncoder.encode(loginRequest.getPassword()));
        return userRepository.save(loginUser);
    }
}
