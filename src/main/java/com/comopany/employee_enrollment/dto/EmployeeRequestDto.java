package com.comopany.employee_enrollment.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeRequestDto {
    @NotBlank
    private String firstName;

    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "\\d{10}")
    private String phone;

    @NotBlank
    private String department;

    private String role;

    @Positive
    private Double salary;

    private LocalDate joiningDate;

    private String address;

    @NotBlank
    private String username;

    @Size(min = 8)
    private String password;
}
