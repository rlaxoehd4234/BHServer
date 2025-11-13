package com.BHServer.www.controller;

import com.BHServer.www.domain.User;
import com.BHServer.www.dto.LoginRequest;
import com.BHServer.www.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User 관리 API ", description = "User 를 관리하는 엔드포인트")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<User> enroll(LoginRequest loginRequest){
        return ResponseEntity.ok(userService.createUser(loginRequest));
    }



}
