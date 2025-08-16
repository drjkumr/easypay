package com.hexaware.easypay.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAcknowledgementDTO {

    private UUID ackId;
    private BigDecimal actualMonthlyPayout;
    private BigDecimal annualIncome;
    private BigDecimal base;
    private BigDecimal ctc;
    private BigDecimal hra;
    private BigDecimal incomeTax;
    private BigDecimal name;
    private BigDecimal oa;
    private BigDecimal pf;
    private BigDecimal prof_tax;
    private int empId;
    private BigDecimal bonus;

    public PaymentAcknowledgementDTO() {
    }

    public PaymentAcknowledgementDTO(UUID ackId, BigDecimal actualMonthlyPayout, BigDecimal annualIncome,
            BigDecimal base, BigDecimal ctc, BigDecimal hra, BigDecimal incomeTax, BigDecimal name, BigDecimal oa,
            BigDecimal pf, BigDecimal prof_tax, int empId, BigDecimal bonus) {
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
        this.prof_tax = prof_tax;
        this.empId = empId;
        this.bonus = bonus;
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

    public BigDecimal getName() {
        return name;
    }

    public void setName(BigDecimal name) {
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

    public BigDecimal getProf_tax() {
        return prof_tax;
    }

    public void setProf_tax(BigDecimal prof_tax) {
        this.prof_tax = prof_tax;
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

}
