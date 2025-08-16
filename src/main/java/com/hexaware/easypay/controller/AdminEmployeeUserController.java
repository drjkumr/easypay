package com.hexaware.easypay.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PasswordChangeDTO;
import com.hexaware.easypay.dto.SalaryPolicyDTO;
import com.hexaware.easypay.dto.UserDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.service.IEmployeeUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminEmployeeUserController {

    //For Admin access only!

    private final IEmployeeUserService employeeUserService;

    //constructor injection
    public AdminEmployeeUserController(IEmployeeUserService employeeUserService) {
        this.employeeUserService = employeeUserService;
    }

    //Here we bundle the creation of employee and user together since they play a crucial role.
    // I created EmployeeUserCreationRequest to bundle together User & Employee creation
    @PostMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public void createEmployeeAndUser(@Valid @RequestBody EmployeeUserCreationRequest req) {
        employeeUserService.createEmployeeandUser(req.getEmployee(), req.getUser());
    }

    //Get any employee by Employee ID
    @GetMapping("/employees/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee getEmployee(@PathVariable int empId) {
        return employeeUserService.getEmployeeById(empId);
    }

    //Get every employee
    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Employee> getAllEmployees() {
        return employeeUserService.getAllEmployees();
    }

    //Get employee by dept
    @GetMapping("/employees/dept/{dept}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Employee> getByDept(@PathVariable String dept) {
        return employeeUserService.getEmployeesByDept(dept);
    }

    //Get employee by role
    @PutMapping("/employees/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateEmployee(@PathVariable int empId, @Valid @RequestBody EmployeeDTO dto) {
        employeeUserService.changeEmployeeDetails(empId, dto);
    }

    //get all users in users table
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return employeeUserService.getAllUsers();
    }

    //get users by empId (Shared pk)
    @GetMapping("/users/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable int empId) {
        return employeeUserService.getUserById(empId);
    }

    //get users by their username
    @GetMapping("/users/username/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserByUsername(@PathVariable String username) {
        return employeeUserService.getUserByUsername(username);
    }

    //Update the user details
    @PutMapping("/users/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUser(@PathVariable int empId, @Valid @RequestBody UserDTO dto) {
        employeeUserService.changeUserDetails(empId, dto);
    }


    //Admin can change anyone's password without knowing old password
    @PutMapping("/users/{empId}/password")
    @PreAuthorize("hasRole('ADMIN')")
    public void changePassword(@PathVariable int empId, @Valid @RequestBody PasswordChangeDTO dto) {
        employeeUserService.changePassword(empId, dto);
    }

    //Get employee and user together
    @GetMapping("/employees/{empId}/emp-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ArrayList<Object> getEmployeeUserTogether(@PathVariable int empId) {
        return employeeUserService.getEmployeeUserDetails(empId);
    }

    //Create salary policy
    //Warning: Set PolicyId only to "1" to avoid breaking the code.
    @PostMapping("/salary-policy")
    @PreAuthorize("hasRole('ADMIN')")
    public void createOrAssignPolicy(@Valid @RequestBody SalaryPolicyDTO dto) {
        employeeUserService.assignSalaryPolicy(dto);
    }
}
