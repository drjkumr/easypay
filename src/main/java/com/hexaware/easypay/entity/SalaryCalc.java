package com.hexaware.easypay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class SalaryCalc {

    @Id
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "annual_income")
    private BigDecimal annualIncome;
    @Column(name = "daily_payout", insertable = false, updatable = false) //derived column don't update/insert
    private BigDecimal dailyPayout;
    @Column(name = "actual_monthly_payout")
    private BigDecimal actualMonthlyPayout;

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

}
