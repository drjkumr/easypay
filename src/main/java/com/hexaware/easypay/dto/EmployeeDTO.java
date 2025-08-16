package com.hexaware.easypay.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {

    @Pattern(regexp = "^\\d+$", message = "Must be a valid ID format")
    @NotNull
    private int empId;
    @Pattern(regexp = "^[A-Za-z]{3,100}}$", message = "Name must contain only letters (A-Z or a-z), max 100 characters, min 3")
    @NotNull
    private String name;
    @Pattern(regexp = "^[A-Za-z]{3,50}}$", message = "Department must contain only letters (A-Z or a-z), max 50 characters, min 3")
    @NotNull
    private String dept;
    @Pattern(regexp = "^[A-Za-z]{3,100}}$", message = "Name must contain only letters (A-Z or a-z), max 100 characters, min 3")
    @NotNull
    private String designation;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid salary format")
    private BigDecimal disbursedSalary;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid ctc format")
    @NotNull
    private BigDecimal ctc;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int empId, String name, String dept, String designation, BigDecimal disbursedSalary,
            BigDecimal ctc) {
        this.empId = empId;
        this.name = name;
        this.dept = dept;
        this.designation = designation;
        this.disbursedSalary = disbursedSalary;
        this.ctc = ctc;
    }

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

    public BigDecimal getDisbursedSalary() {
        return disbursedSalary;
    }

    public void setDisbursedSalary(BigDecimal disbursedSalary) {
        this.disbursedSalary = disbursedSalary;
    }

    public BigDecimal getCtc() {
        return ctc;
    }

    public void setCtc(BigDecimal ctc) {
        this.ctc = ctc;
    }

}
