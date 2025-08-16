package com.hexaware.easypay.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SalaryCalcDTO {

    @NotNull
    @Pattern(regexp = "^\\d+$", message = "Must be a valid ID format")
    private int empId;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid annual income format")
    private BigDecimal annualIncome;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid daily payout format")
    private BigDecimal dailyPayout;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid monthly payout format")
    private BigDecimal actualMonthlyPayout;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid leave deduction format")
    private BigDecimal leaveDeduction;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid performance additions format")
    private BigDecimal performanceAdditions;

    public SalaryCalcDTO() {
    }

    public SalaryCalcDTO(int empId, BigDecimal annualIncome, BigDecimal dailyPayout, BigDecimal actualMonthlyPayout,
            BigDecimal leaveDeduction, BigDecimal performanceAdditions) {
        this.empId = empId;
        this.annualIncome = annualIncome;
        this.dailyPayout = dailyPayout;
        this.actualMonthlyPayout = actualMonthlyPayout;
        this.leaveDeduction = leaveDeduction;
        this.performanceAdditions = performanceAdditions;
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

    public BigDecimal getLeaveDeduction() {
        return leaveDeduction;
    }

    public void setLeaveDeduction(BigDecimal leaveDeduction) {
        this.leaveDeduction = leaveDeduction;
    }

    public BigDecimal getPerformanceAdditions() {
        return performanceAdditions;
    }

    public void setPerformanceAdditions(BigDecimal performanceAdditions) {
        this.performanceAdditions = performanceAdditions;
    }

}
