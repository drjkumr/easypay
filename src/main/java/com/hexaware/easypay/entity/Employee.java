package com.hexaware.easypay.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "employee")
@ToString
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
    @Column(name = "disbursed_salary")
    private BigDecimal disbursedSalary;
    @Column(name="ctc")
    private BigDecimal ctc;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private LeaveCalc leaveCalc;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SalaryComp salaryComp;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SalaryCalc salaryCalc;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Performance performance;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentAcknowledgement> acknowledgements = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

    

    public Employee() {
    }

    public Employee(int empId, String name, String dept, String designation, BigDecimal disbursedSalary, BigDecimal ctc,
            User user, LeaveCalc leaveCalc, SalaryComp salaryComp, SalaryCalc salaryCalc, Performance performance,
            List<PaymentAcknowledgement> acknowledgements) {
        this.empId = empId;
        this.name = name;
        this.dept = dept;
        this.designation = designation;
        this.disbursedSalary = disbursedSalary;
        this.ctc = ctc;
        this.user = user;
        this.leaveCalc = leaveCalc;
        this.salaryComp = salaryComp;
        this.salaryCalc = salaryCalc;
        this.performance = performance;
        this.acknowledgements = acknowledgements;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LeaveCalc getLeaveCalc() {
        return leaveCalc;
    }

    public void setLeaveCalc(LeaveCalc leaveCalc) {
        this.leaveCalc = leaveCalc;
    }

    public SalaryComp getSalaryComp() {
        return salaryComp;
    }

    public void setSalaryComp(SalaryComp salaryComp) {
        this.salaryComp = salaryComp;
    }

    public SalaryCalc getSalaryCalc() {
        return salaryCalc;
    }

    public void setSalaryCalc(SalaryCalc salaryCalc) {
        this.salaryCalc = salaryCalc;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public List<PaymentAcknowledgement> getAcknowledgements() {
        return acknowledgements;
    }

    public void setAcknowledgements(List<PaymentAcknowledgement> acknowledgements) {
        this.acknowledgements = acknowledgements;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    



}
