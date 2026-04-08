package com.comopany.employee_enrollment.service;

import com.comopany.employee_enrollment.dto.EmployeeRequestDto;
import com.comopany.employee_enrollment.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto enroll(EmployeeRequestDto employeeRequestDto);
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeById(Long id, String username,String role);
    EmployeeResponseDto updateEmployee(Long id,EmployeeRequestDto request);
    EmployeeResponseDto updateStatus(Long id,String status);
    EmployeeResponseDto getEmployeeByUsername(String username);

}
