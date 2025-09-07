package com.hexaware.easypay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="payout_calc")
@NoArgsConstructor
@AllArgsConstructor
public class PayoutCalc {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="payout_id")
    private Long payoutId;
    @Column(name="emp_id")
    private Long empId;
    @Column(name="base")
    private BigDecimal base;
    @Column(name="hra")
    private BigDecimal hra;
    @Column(name="ta")
    private BigDecimal ta;
    @Column(name="oa")
    private BigDecimal oa;
    @Column(name="pf")
    private BigDecimal pf;
    @Column(name="prof_tax")
    private BigDecimal profTax;
    @Column(name="income_tax")
    private BigDecimal incomeTax;
    @Column(name="gross")
    private BigDecimal gross;
    @Column(name="monthly")
    private BigDecimal monthly;
    @Column(name="daily")
    private BigDecimal daily;

    @ManyToOne
    @JoinColumn(name="emp_id", insertable=false, updatable=false)
    private Employee employee;

}
