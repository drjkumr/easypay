package com.hexaware.easypay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="payroll")
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

    @Id
    @Column(name="emp_id")
    private Long empId;
    @Column(name="active")
    private Boolean active;
    @Column(name="policy_id")
    private Long policyId;

    @OneToOne
    @MapsId
    @JoinColumn(name="emp_id")
    private Employee employee;

}
