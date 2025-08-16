package com.hexaware.easypay.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @Pattern(regexp = "^\\d+$", message = "Must be a valid ID format")
    @NotNull
    private int empId;
    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username must be alphanumeric")
    private String username;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$", message = "Password should be minimum length of 7, with special character, Uppercase, Lowercase and Number.")
    private String password;
    @NotNull
    @Pattern(regexp = "^(admin|payroll_processor|employee|manager)$", message = "Access level must be one of: admin, payroll_processor, employee, manager")
    private String accessLevel;

    public UserDTO() {
    }

    public UserDTO(int empId, String username, String password, String accessLevel) {
        this.empId = empId;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

}
