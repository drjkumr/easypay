package com.hexaware.easypay.service;

import java.util.ArrayList;
import java.util.List;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PasswordChangeDTO;
import com.hexaware.easypay.dto.SalaryPolicyDTO;
import com.hexaware.easypay.dto.UserDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.User;

public interface IEmployeeUserService {

    public Employee getEmployeeById(int empId);

    public List<Employee> getEmployeesByDept(String dept);

    public List<Employee> getAllEmployees();

    public void createEmployeeandUser(EmployeeDTO empDto, UserDTO userDto);

    public User getUserById(int empId);

    public List<User> getAllUsers();

    public User getUserByUsername(String username);

    public ArrayList<Object> getEmployeeUserDetails(int empId);

    public void changeEmployeeDetails(int empId, EmployeeDTO dto);

    public void changeUserDetails(int empId, UserDTO dto);

    public void changePassword(int empId, PasswordChangeDTO dto);

    public void assignSalaryPolicy(SalaryPolicyDTO dto);

}
