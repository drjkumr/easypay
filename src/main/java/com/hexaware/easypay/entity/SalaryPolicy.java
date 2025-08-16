package com.hexaware.easypay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salary_policy")
public class SalaryPolicy {

    @Id
    @Column(name = "policy_id")
    private int policyId;
    @Column(name = "base_percent")
    private BigDecimal basePercent;
    @Column(name = "hra_percent")
    private BigDecimal hraPercent;
    @Column(name = "ta_percent")
    private BigDecimal taPercent;
    @Column(name = "oa_percent")
    private BigDecimal oaPercent;
    @Column(name = "pf_percent")
    private BigDecimal pfPercent;
    @Column(name = "income_tax_percent")
    private BigDecimal incomeTaxPercent;
    @Column(name = "prof_tax_fixed")
    private BigDecimal profTaxFixed;

    public SalaryPolicy() {
    }

    public SalaryPolicy(int policyId, BigDecimal basePercent, BigDecimal hraPercent, BigDecimal taPercent,
            BigDecimal oaPercent, BigDecimal pfPercent, BigDecimal incomeTaxPercent, BigDecimal profTaxFixed) {
        this.policyId = policyId;
        this.basePercent = basePercent;
        this.hraPercent = hraPercent;
        this.taPercent = taPercent;
        this.oaPercent = oaPercent;
        this.pfPercent = pfPercent;
        this.incomeTaxPercent = incomeTaxPercent;
        this.profTaxFixed = profTaxFixed;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public BigDecimal getBasePercent() {
        return basePercent;
    }

    public void setBasePercent(BigDecimal basePercent) {
        this.basePercent = basePercent;
    }

    public BigDecimal getHraPercent() {
        return hraPercent;
    }

    public void setHraPercent(BigDecimal hraPercent) {
        this.hraPercent = hraPercent;
    }

    public BigDecimal getTaPercent() {
        return taPercent;
    }

    public void setTaPercent(BigDecimal taPercent) {
        this.taPercent = taPercent;
    }

    public BigDecimal getOaPercent() {
        return oaPercent;
    }

    public void setOaPercent(BigDecimal oaPercent) {
        this.oaPercent = oaPercent;
    }

    public BigDecimal getPfPercent() {
        return pfPercent;
    }

    public void setPfPercent(BigDecimal pfPercent) {
        this.pfPercent = pfPercent;
    }

    public BigDecimal getIncomeTaxPercent() {
        return incomeTaxPercent;
    }

    public void setIncomeTaxPercent(BigDecimal incomeTaxPercent) {
        this.incomeTaxPercent = incomeTaxPercent;
    }

    public BigDecimal getProfTaxFixed() {
        return profTaxFixed;
    }

    public void setProfTaxFixed(BigDecimal profTaxFixed) {
        this.profTaxFixed = profTaxFixed;
    }

}
