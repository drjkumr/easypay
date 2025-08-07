package com.hexaware.easypay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@ToString
@Table(name="salary_comp")
public class SalaryComp {

    @Id
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "ctc")
    private BigDecimal ctc;
    @Column(name = "base")
    private BigDecimal base;
    @Column(name = "hra")
    private BigDecimal hra;
    @Column(name = "ta")
    private BigDecimal ta;
    @Column(name = "oa")
    private BigDecimal oa;
    @Column(name = "pf")
    private BigDecimal pf;
    @Column(name = "prof_tax")
    private BigDecimal profTax;
    @Column(name = "income_tax")
    private BigDecimal incomeTax;

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

}
