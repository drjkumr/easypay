package com.hexaware.easypay.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public class LeaveRequestDTO {

    @NotNull
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
    message = "Must be a valid UUID") //Pattern validation for UUID
    private UUID reqId;
    @NotNull
     @Pattern(regexp = "^\\d+$", message = "Must be a valid ID format")
    private int empId;
    @PastOrPresent
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    
    private String leaveType;
    private String approvalStatus;
    private LocalDate requestDate;
    private LocalDate approveDate;
@Pattern(regexp = "^[A-Za-z0-9]+{1,255}$", message = "Must contain only letters and numbers")
    private String reason;
@Pattern(regexp = "^[A-Za-z0-9]+{1,255}$", message = "Must contain only letters and numbers")
    private String ApprovalRejectionReason;

    public LeaveRequestDTO() {
    }

    public LeaveRequestDTO(UUID reqId, int empId, LocalDate startDate, LocalDate endDate, String leaveType,
            String approvalStatus, LocalDate requestDate, LocalDate approveDate, String reason,
            String approvalRejectionReason) {
        this.reqId = reqId;
        this.empId = empId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.approvalStatus = approvalStatus;
        this.requestDate = requestDate;
        this.approveDate = approveDate;
        this.reason = reason;
        ApprovalRejectionReason = approvalRejectionReason;
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

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
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
        return ApprovalRejectionReason;
    }

    public void setApprovalRejectionReason(String approvalRejectionReason) {
        ApprovalRejectionReason = approvalRejectionReason;
    }

}
