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
@Table(name="payslip")
@NoArgsConstructor
@AllArgsConstructor
public class Payslip {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="payslip_id")
    private Long payslipId;
    @Column(name="emp_id")
    private Long empId;
    @Column(name="actual_monthly_payout")
    private BigDecimal actualMonthlyPayout;
    @Column(name="benefits")
    private BigDecimal benefits;

     @ManyToOne
    @JoinColumn(name="emp_id", insertable=false, updatable=false)
    private Employee employee;

}
