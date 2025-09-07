package com.hexaware.easypay.entity;
<<<<<<< HEAD
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="leave_request")
public class LeaveRequest {

    @Id
    @Column(name = "req_id", columnDefinition="BINARY(16)")
    private UUID reqId;
    @Column(name="emp_id")
    private int empId;
    @Column(name="start_date")
    private  LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="approval_status")
    private String approvalStatus;
    @Column(name="request_date")
    private LocalDate requestDate;
    @Column(name="approve_date")
    private LocalDate approveDate;
    @Column(name="reason")
    private String reason;
    @Column(name="approval_rejection_reason")
    private String approvalRejectionReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @OneToOne(mappedBy = "leave_request", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private LeaveCalc leaveCalc;
    
    public LeaveRequest() {}

    public LeaveRequest(UUID reqId, int empId, LocalDate startDate, LocalDate endDate, String approvalStatus,
            LocalDate requestDate, LocalDate approveDate, String reason, String approvalRejectionReason,
            Employee employee, LeaveCalc leaveCalc) {
        this.reqId = reqId;
        this.empId = empId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approvalStatus = approvalStatus;
        this.requestDate = requestDate;
        this.approveDate = approveDate;
        this.reason = reason;
        this.approvalRejectionReason = approvalRejectionReason;
        this.employee = employee;
        this.leaveCalc = leaveCalc;
    }

    public UUID getReqId() {
        return reqId;
    }

    public void setReqId(UUID reqId) {
        this.reqId = reqId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(LocalDate approveDate) {
        this.approveDate = approveDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprovalRejectionReason() {
        return approvalRejectionReason;
    }

    public void setApprovalRejectionReason(String approvalRejectionReason) {
        this.approvalRejectionReason = approvalRejectionReason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveCalc getLeaveCalc() {
        return leaveCalc;
    }

    public void setLeaveCalc(LeaveCalc leaveCalc) {
        this.leaveCalc = leaveCalc;
    }

    
    
    

=======

import java.time.LocalDate;

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
@Table(name="leave_request")
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="req_id")
    private Long reqId;
    @Column(name="emp_id")
    private Long empId;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="reason")
    private String reason;
    @Column(name="approval")
    private Boolean approval;

    @ManyToOne
    @JoinColumn(name="emp_id", insertable=false, updatable=false)
    private Employee employee;

>>>>>>> 314dc4c (Updated latest backend)

}
