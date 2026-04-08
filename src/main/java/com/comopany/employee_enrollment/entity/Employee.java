package com.comopany.employee_enrollment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employeess")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;
    private String department;
    private String role;
    private Double salary;

    private LocalDate joiningDate;
    private String address;

    @Column(unique = true)
    private String username;

    private String password;
    private String status;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


}
