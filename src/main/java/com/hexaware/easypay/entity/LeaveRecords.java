package com.hexaware.easypay.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name="leave_records")
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRecords {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="leave_id")
    private Long leaveId;
    @Column(name="emp_id")
    private Long empId;
    @Column(name="leave_date")
    private LocalDate leaveDate;
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="emp_id", insertable=false, updatable=false)
    private Employee employee;

}
