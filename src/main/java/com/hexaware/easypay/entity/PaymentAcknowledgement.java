package com.hexaware.easypay.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "payment_acknowledgement")
@ToString
public class PaymentAcknowledgement {

    @Id
    @Column(name = "ack_id")
    private UUID ackId;
    @Column(name = "actual_monthly_payout")
    private BigDecimal actualMonthlyPayout;
    @Column(name = "annual_income")
    private BigDecimal annualIncome;
    @Column(name = "base")
    private BigDecimal base;
    @Column(name = "ctc")
    private BigDecimal ctc;
    @Column(name = "hra")
    private BigDecimal hra;
    @Column(name = "income_tax")
    private BigDecimal incomeTax;
    @Column(name = "name")
    private String name;
    @Column(name = "oa")
    private BigDecimal oa;
    @Column(name = "pf")
    private BigDecimal pf;
    @Column(name = "prof_tax")
    private BigDecimal profTax;
    @Column(name = "ta")
    private BigDecimal ta;
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "bonus")
    private BigDecimal bonus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    public PaymentAcknowledgement() {
    }

    public PaymentAcknowledgement(UUID ackId, BigDecimal actualMonthlyPayout, BigDecimal annualIncome, BigDecimal base,
            BigDecimal ctc, BigDecimal hra, BigDecimal incomeTax, String name, BigDecimal oa, BigDecimal pf,
            BigDecimal profTax, BigDecimal ta, int empId, BigDecimal bonus, Employee employee) {
        this.ackId = ackId;
        this.actualMonthlyPayout = actualMonthlyPayout;
        this.annualIncome = annualIncome;
        this.base = base;
        this.ctc = ctc;
        this.hra = hra;
        this.incomeTax = incomeTax;
        this.name = name;
        this.oa = oa;
        this.pf = pf;
        this.profTax = profTax;
        this.ta = ta;
        this.empId = empId;
        this.bonus = bonus;
        this.employee = employee;
    }

    public UUID getAckId() {
        return ackId;
    }

    public void setAckId(UUID ackId) {
        this.ackId = ackId;
    }

    public BigDecimal getActualMonthlyPayout() {
        return actualMonthlyPayout;
    }

    public void setActualMonthlyPayout(BigDecimal actualMonthlyPayout) {
        this.actualMonthlyPayout = actualMonthlyPayout;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getCtc() {
        return ctc;
    }

    public void setCtc(BigDecimal ctc) {
        this.ctc = ctc;
    }

    public BigDecimal getHra() {
        return hra;
    }

    public void setHra(BigDecimal hra) {
        this.hra = hra;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getTa() {
        return ta;
    }

    public void setTa(BigDecimal ta) {
        this.ta = ta;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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
