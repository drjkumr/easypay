package com.hexaware.easypay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@ToString
@Table(name="user")
public class User {

    @Id
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "username")
    private String username;
    @Column(name = "psw")
    private String psw;

    

    public User(int empId, String username, String psw) {
        this.empId = empId;
        this.username = username;
        this.psw = psw;
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

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

}
