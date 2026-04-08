package com.comopany.employee_enrollment.service;

import com.comopany.employee_enrollment.dto.LoginRequestDto;
import com.comopany.employee_enrollment.entity.Employee;
import com.comopany.employee_enrollment.repository.EmployeeRepository;
import com.comopany.employee_enrollment.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService{
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(LoginRequestDto request) {

        Employee emp = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(request.getPassword(), emp.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        if(!emp.getStatus().equals("ACTIVE")){
            throw new RuntimeException("Account is inactive");
        }

        return jwtUtil.generateToken(emp.getUsername(), emp.getRole());
    }
}
