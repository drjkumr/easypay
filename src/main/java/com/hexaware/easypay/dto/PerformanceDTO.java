package com.hexaware.easypay.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PerformanceDTO {

    @NotNull
    @Pattern(regexp = "^\\d+$", message = "Must be a valid ID format")
    private int empId;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid performance format")
    private BigDecimal performance;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid bonus percent format")
    private BigDecimal bonusPercent;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid bonus format")
    private BigDecimal bonus;

    public PerformanceDTO() {
    }

    public PerformanceDTO(int empId, BigDecimal performance, BigDecimal bonusPercent, BigDecimal bonus) {
        this.empId = empId;
        this.performance = performance;
        this.bonusPercent = bonusPercent;
        this.bonus = bonus;
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

}
