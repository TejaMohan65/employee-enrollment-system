package com.comopany.employee_enrollment.controller;

import com.comopany.employee_enrollment.dto.ApiResponse;
import com.comopany.employee_enrollment.dto.LoginRequestDto;
import com.comopany.employee_enrollment.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequestDto request) {

        String token = authService.login(request);

        return new ApiResponse<>(200, "Login Successful", token, LocalDateTime.now());
    }
}
