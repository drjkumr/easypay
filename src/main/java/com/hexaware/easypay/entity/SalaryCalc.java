package com.hexaware.easypay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "salary_calc")
@ToString
public class SalaryCalc {

    @Id
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "annual_income")
    private BigDecimal annualIncome;
    @Column(name = "daily_payout")
    private BigDecimal dailyPayout;
    @Column(name = "actual_monthly_payout")
    private BigDecimal actualMonthlyPayout;
    @Column(name = "leave_deductions")
    private BigDecimal leaveDeductions;
    @Column(name = "performance_additions")
    private BigDecimal performanceAdditions;

    @OneToOne
    @MapsId
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public SalaryCalc() {
    }

    public SalaryCalc(int empId, BigDecimal annualIncome, BigDecimal dailyPayout, BigDecimal actualMonthlyPayout,
            BigDecimal leaveDeductions, BigDecimal performanceAdditions, Employee employee) {
        this.empId = empId;
        this.annualIncome = annualIncome;
        this.dailyPayout = dailyPayout;
        this.actualMonthlyPayout = actualMonthlyPayout;
        this.leaveDeductions = leaveDeductions;
        this.performanceAdditions = performanceAdditions;
        this.employee = employee;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public BigDecimal getDailyPayout() {
        return dailyPayout;
    }

    public void setDailyPayout(BigDecimal dailyPayout) {
        this.dailyPayout = dailyPayout;
    }

    public BigDecimal getActualMonthlyPayout() {
        return actualMonthlyPayout;
    }

    public void setActualMonthlyPayout(BigDecimal actualMonthlyPayout) {
        this.actualMonthlyPayout = actualMonthlyPayout;
    }

    public BigDecimal getLeaveDeductions() {
        return leaveDeductions;
    }

    public void setLeaveDeductions(BigDecimal leaveDeductions) {
        this.leaveDeductions = leaveDeductions;
    }

    public BigDecimal getPerformanceAdditions() {
        return performanceAdditions;
    }

    public void setPerformanceAdditions(BigDecimal performanceAdditions) {
        this.performanceAdditions = performanceAdditions;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
