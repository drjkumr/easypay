package com.hexaware.easypay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PasswordChangeDTO;
import com.hexaware.easypay.dto.SalaryPolicyDTO;
import com.hexaware.easypay.dto.UserDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.SalaryPolicy;
import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.exception.EmployeeNotFoundException;
import com.hexaware.easypay.exception.UsernameNotFoundException;
import com.hexaware.easypay.repository.EmployeeRepository;
import com.hexaware.easypay.repository.SalaryPolicyRepository;
import com.hexaware.easypay.repository.UserRepository;

import jakarta.transaction.Transactional;

/*
 * -------------------------------------------------------------------
 *  Created by: Dheeraj
 *  Under the guidance of: Trainer Javeed Sir
 *  Organization: Hexaware
 * -------------------------------------------------------------------
 */

//Admin use only!

@Service
@SuppressWarnings("unused")
public class EmployeeUserService implements IEmployeeUserService {

    //Autowired issue so direct controller injection
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SalaryPolicyRepository salaryPolicyRepository;

    public EmployeeUserService(EmployeeRepository employeeRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, SalaryPolicyRepository salaryPolicyRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.salaryPolicyRepository = salaryPolicyRepository;
    }

    @Override
    public Employee getEmployeeById(int empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
    }

    @Override
    public List<Employee> getEmployeesByDept(String dept) {

        List<Employee> employees = employeeRepository.findByDept(dept);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(dept);
        }
        return employees;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // User and employee are created together password is hashed by BCrypt and
    // stored.
    @Override
    @Transactional
    public void createEmployeeandUser(EmployeeDTO empDto, UserDTO userDto) {

        Employee employee = new Employee();
        employee.setName(empDto.getName());
        employee.setDept(empDto.getDept());
        employee.setDesignation(empDto.getDesignation());
        employee.setDisbursedSalary(BigDecimal.valueOf(0));
        employee.setCtc(empDto.getCtc());

        Employee savedEmployee = employeeRepository.save(employee);

        User user = new User();
        user.setEmpId(savedEmployee.getEmpId());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAccessLevel(userDto.getAccessLevel());

        userRepository.save(user);

        empDto.setEmpId(savedEmployee.getEmpId());

    }

    @Override
    public User getUserById(int empId) {
        return userRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public ArrayList<Object> getEmployeeUserDetails(int empId) {
        ArrayList<Object> empUser = new ArrayList<>();
        empUser.add(employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId)));
        empUser.add(userRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId)));

        return empUser;
    }

    @Override
    @Transactional
    public void changeEmployeeDetails(int empId, EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));

        employee.setName(dto.getName());
        employee.setDesignation(dto.getDesignation());
        employee.setDept(dto.getDept());
        employee.setCtc(dto.getCtc());

        Employee updated = employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void changeUserDetails(int empId, UserDTO dto) {
        User user = userRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));

        user.setUsername(dto.getUsername());
        user.setAccessLevel(dto.getAccessLevel());

        User updated = userRepository.save(user);
    }

    // Changing the encrypted password using Bcrypt
    @Override
    @Transactional
    public void changePassword(int empId, PasswordChangeDTO dto) {
        User user = userRepository.findById(empId).orElseThrow(() -> new UsernameNotFoundException(empId));

        String encoded = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(encoded);

        // This is for user
        // if(dto.getOldPassword() != null) {
        // // if(!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())){
        // // throw new PasswordMismatchException("Passwords do not match. Please try
        // again!!");
        // // }
        // }
    }

    @Transactional
    @Override
    //Setting the salary Policy that is percentages of components that can be used in calculation
    public void assignSalaryPolicy(SalaryPolicyDTO dto) {
        SalaryPolicy policy = new SalaryPolicy(
        
                dto.getPolicyId(),
                dto.getBasePercent(),
                dto.getHraPercent(),
                dto.getTaPercent(),
                dto.getOaPercent(),
                dto.getPfPercent(),
                dto.getIncomeTaxPercent(),
                dto.getProfTaxFixed()
                
                );

        salaryPolicyRepository.save(policy);
    }

}
