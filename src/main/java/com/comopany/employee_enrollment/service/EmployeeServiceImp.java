package com.comopany.employee_enrollment.service;

import com.comopany.employee_enrollment.dto.EmployeeRequestDto;
import com.comopany.employee_enrollment.dto.EmployeeResponseDto;
import com.comopany.employee_enrollment.entity.Employee;
import com.comopany.employee_enrollment.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService{
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeResponseDto enroll(EmployeeRequestDto request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (repository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Employee emp = new Employee();

        BeanUtils.copyProperties(request, emp);

        emp.setPassword(passwordEncoder.encode(request.getPassword()));
        emp.setStatus("ACTIVE");
        emp.setCreatedDate(LocalDateTime.now());

        repository.save(emp);

        return mapToDTO(emp);
    }

    private EmployeeResponseDto mapToDTO(Employee emp) {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        BeanUtils.copyProperties(emp, dto);
        return dto;
    }
    @Override
    public List<EmployeeResponseDto> getAllEmployees(){
        List<Employee> employees = repository.findAll();
        return  employees.stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id,String username,String role){
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if(!role.equals("ADMIN") && !emp.getUsername().equals(username)){
            throw new RuntimeException("Access Denied");
        }
        return mapToDTO(emp);
    }
    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {

        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setFirstName(request.getFirstName());
        emp.setLastName(request.getLastName());
        emp.setEmail(request.getEmail());
        emp.setPhone(request.getPhone());
        emp.setDepartment(request.getDepartment());
        emp.setSalary(request.getSalary());
        emp.setAddress(request.getAddress());

        emp.setUpdatedDate(LocalDateTime.now());

        return mapToDTO(repository.save(emp));
    }
    @Override
    public EmployeeResponseDto updateStatus(Long id, String status) {

        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));


        if (!status.equalsIgnoreCase("ACTIVE") && !status.equalsIgnoreCase("INACTIVE")) {
            throw new RuntimeException("Invalid status value");
        }

        emp.setStatus(status.toUpperCase());
        emp.setUpdatedDate(LocalDateTime.now());

        return mapToDTO(repository.save(emp));
    }
    @Override
    public EmployeeResponseDto getEmployeeByUsername(String username) {

        Employee emp = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return mapToDTO(emp);
    }

}
