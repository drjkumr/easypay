package com.hexaware.easypay.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@ToString
@Table(name="payout_acknowledgement")
public class PayoutAcknowledgement {

    @Column(name = "emp_id")
    private int empId;
    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "ack_id")
    private UUID ackId;
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
    @Column(name = "annual_income")
    private BigDecimal annualIncome;
    @Column(name = "actual_monthly_payout")
    private BigDecimal actualMonthlyPayout;
    @Column(name = "generated_at")
    private LocalDateTime generatedAt;
    @Column(name = "generated_by_username")
    private String generatedByUsername;

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

    public UUID getAckId() {
        return ackId;
    }

    public void setAckId(UUID ackId) {
        this.ackId = ackId;
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

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public BigDecimal getActualMonthlyPayout() {
        return actualMonthlyPayout;
    }

    public void setActualMonthlyPayout(BigDecimal actualMonthlyPayout) {
        this.actualMonthlyPayout = actualMonthlyPayout;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getGeneratedByUsername() {
        return generatedByUsername;
    }

    public void setGeneratedByUsername(String generatedByUsername) {
        this.generatedByUsername = generatedByUsername;
    }

}
