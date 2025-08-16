package com.hexaware.easypay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "performance")
@ToString
public class Performance {

    @Column(name = "emp_id")
    private int empId;
    @Column(name = "performance")
    private BigDecimal performance;
    @Column(name = "bonus_percent")
    private BigDecimal bonusPercent;
    @Column(name = "bonus")
    private BigDecimal bonus;

    @OneToOne
    @MapsId
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Performance() {
    }

    public Performance(int empId, BigDecimal performance, BigDecimal bonusPercent, BigDecimal bonus,
            Employee employee) {
        this.empId = empId;
        this.performance = performance;
        this.bonusPercent = bonusPercent;
        this.bonus = bonus;
        this.employee = employee;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public BigDecimal getPerformance() {
        return performance;
    }

    public void setPerformance(BigDecimal performance) {
        this.performance = performance;
    }

    public BigDecimal getBonusPercent() {
        return bonusPercent;
    }

    public void setBonusPercent(BigDecimal bonusPercent) {
        this.bonusPercent = bonusPercent;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
