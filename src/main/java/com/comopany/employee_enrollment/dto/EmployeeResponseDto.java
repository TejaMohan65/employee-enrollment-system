package com.comopany.employee_enrollment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeResponseDto {
    private Long empId;

    private String firstName;
    private String lastName;

    private String email;
    private String phone;

    private String department;
    private String role;

    private Double salary;

    private LocalDate joiningDate;

    private String address;

    private String username;

    private String status;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
