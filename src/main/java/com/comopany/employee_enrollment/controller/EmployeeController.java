package com.comopany.employee_enrollment.controller;

import com.comopany.employee_enrollment.dto.ApiResponse;
import com.comopany.employee_enrollment.dto.EmployeeRequestDto;
import com.comopany.employee_enrollment.dto.EmployeeResponseDto;
import com.comopany.employee_enrollment.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping("/enroll")
    public ApiResponse<EmployeeResponseDto> enroll(@Valid @RequestBody EmployeeRequestDto request) {

        EmployeeResponseDto response = service.enroll(request);

        return new ApiResponse<>(200, "Employee Enrolled", response, LocalDateTime.now());
    }
    @GetMapping
    public ApiResponse<List<EmployeeResponseDto>> getAll(){
        List<EmployeeResponseDto> list = service.getAllEmployees();
        return new ApiResponse<>(200,"All Employees",list,LocalDateTime.now());
    }
    @GetMapping("/{id}")
    public ApiResponse<EmployeeResponseDto> getById(
            @PathVariable Long id,
            Authentication authentication) {

        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        role = role.replace("ROLE_", "");

        EmployeeResponseDto response =
                service.getEmployeeById(id, username, role);

        return new ApiResponse<>(200, "Employee Found", response, LocalDateTime.now());
    }
    @PutMapping("/{id}")
    public ApiResponse<EmployeeResponseDto> update(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDto request) {

        EmployeeResponseDto response = service.updateEmployee(id, request);

        return new ApiResponse<>(200, "Employee Updated", response, LocalDateTime.now());
    }
    @PutMapping("/{id}/status")
    public ApiResponse<EmployeeResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        EmployeeResponseDto response = service.updateStatus(id, status);

        return new ApiResponse<>(200, "Status Updated", response, LocalDateTime.now());
    }
    @GetMapping("/me")
    public ApiResponse<EmployeeResponseDto> getMyProfile(Authentication authentication) {

        String username = authentication.getName();

        EmployeeResponseDto response = service.getEmployeeByUsername(username);

        return new ApiResponse<>(
                200,
                "My Profile",
                response,
                LocalDateTime.now()
        );
    }
}
