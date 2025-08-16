package com.hexaware.easypay.controller;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.UserDTO;

//This is used to bind the creation of the employee and user together in AdminEmployeeUserController because we bundled both's creation in servic class

public class EmployeeUserCreationRequest {
    private EmployeeDTO employee;
    private UserDTO user;

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
