package com.hexaware.easypay.entity;

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
@Table(name="timesheet")
@NoArgsConstructor
@AllArgsConstructor
public class Timesheet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="timesheet_id")
    private Long timesheetId;
    @Column(name="emp_id")
    private Long empId;
    @Column(name="no_days_worked")
    private Integer noDaysWorked;
    @Column(name="approval")
    private Boolean approval;

    @ManyToOne
    @JoinColumn(name="emp_id", insertable=false, updatable=false)
    private Employee employee;

}
