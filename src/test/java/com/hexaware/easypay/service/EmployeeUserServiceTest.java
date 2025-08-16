package com.hexaware.easypay.service;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.UserDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.repository.EmployeeRepository;
import com.hexaware.easypay.repository.UserRepository;

public class EmployeeUserServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private EmployeeUserService employeeUserService;


    @Test
    void testCreateEmployeeAndUser_Success() {
        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setName("Alice");
        empDto.setDept("Engineering");
        empDto.setDesignation("Senior Developer");
        empDto.setCtc(BigDecimal.valueOf(75000));

        UserDTO userDto = new UserDTO();
        userDto.setUsername("alice123");
        userDto.setPassword("securePass");
        userDto.setAccessLevel("ADMIN");

        Employee savedEmployee = new Employee();
        savedEmployee.setEmpId(123);
        savedEmployee.setName("Alice");

        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);
        when(passwordEncoder.encode("securePass")).thenReturn("hashed_securePass");

        employeeUserService.createEmployeeandUser(empDto, userDto);

        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(userRepository, times(1)).save(any());
        assertEquals(123, empDto.getEmpId());
    }
}