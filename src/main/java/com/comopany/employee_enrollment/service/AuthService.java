package com.comopany.employee_enrollment.service;

import com.comopany.employee_enrollment.dto.LoginRequestDto;

public interface AuthService {
    String login(LoginRequestDto loginRequestDto);
}
