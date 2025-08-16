package com.hexaware.easypay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "user")
@ToString
public class User {

    @Id
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "access_level")
    private String accessLevel;
    

    @OneToOne
    @MapsId
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public User() {
    }

    public User(int empId, String username, String password, String accessLevel, Employee employee) {
        this.empId = empId;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    

}
