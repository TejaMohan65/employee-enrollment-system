package com.comopany.employee_enrollment;

import com.comopany.employee_enrollment.entity.Employee;
import com.comopany.employee_enrollment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        if (!repository.existsByUsername("admin")) {

            Employee admin = new Employee();

            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@gmail.com");
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin.setStatus("ACTIVE");
            admin.setCreatedDate(LocalDateTime.now());

            repository.save(admin);

            System.out.println(" Default Admin Created");
        }
    }

}
