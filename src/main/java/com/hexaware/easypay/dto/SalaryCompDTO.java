package com.hexaware.easypay.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SalaryCompDTO {

    @NotNull
    @Pattern(regexp = "^\\d+$", message = "Must be a valid ID format")
    private int empId;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal ctc;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal base;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal hra;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal ta;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal oa;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal pf;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal profTax;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal incomeTax;
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal bonus;

    public SalaryCompDTO() {
    }

    public SalaryCompDTO(int empId, BigDecimal ctc, BigDecimal base, BigDecimal hra, BigDecimal ta, BigDecimal oa,
            BigDecimal pf, BigDecimal profTax, BigDecimal incomeTax, BigDecimal bonus) {
        this.empId = empId;
        this.ctc = ctc;
        this.base = base;
        this.hra = hra;
        this.ta = ta;
        this.oa = oa;
        this.pf = pf;
        this.profTax = profTax;
        this.incomeTax = incomeTax;
        this.bonus = bonus;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public BigDecimal getCtc() {
        return ctc;
    }

    public void setCtc(BigDecimal ctc) {
        this.ctc = ctc;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getHra() {
        return hra;
    }

    public void setHra(BigDecimal hra) {
        this.hra = hra;
    }

    public BigDecimal getTa() {
        return ta;
    }

    public void setTa(BigDecimal ta) {
        this.ta = ta;
    }

    public BigDecimal getOa() {
        return oa;
    }

    public void setOa(BigDecimal oa) {
        this.oa = oa;
    }

    public BigDecimal getPf() {
        return pf;
    }

    public void setPf(BigDecimal pf) {
        this.pf = pf;
    }

    public BigDecimal getProfTax() {
        return profTax;
    }

    public void setProfTax(BigDecimal profTax) {
        this.profTax = profTax;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

}
