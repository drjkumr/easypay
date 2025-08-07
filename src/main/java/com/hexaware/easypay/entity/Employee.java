package com.hexaware.easypay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@ToString
@Table(name="employee")
public class Employee {

    @Id
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "name")
    private String name;
    @Column(name = "dept")
    private String dept;
    @Column(name = "designation")
    private String designation;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}
