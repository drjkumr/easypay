package com.hexaware.easypay.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "leave_calc")
@ToString
public class LeaveCalc {

    @Id
    @Column(name = "req_id", columnDefinition="BINARY(16)")
    private UUID reqId;
    @Column(name = "paid_leaves")
    private int paidLeaves;
    @Column(name = "unpaid_leaves")
    private int unpaidLeaves;
    @Column(name = "balance_leaves")
    private int balanceLeaves;
    @Column(name = "total_leaves")
    private int totalLeaves;

    @OneToOne
    @MapsId
    @JoinColumn(name = "req_id")
    private LeaveRequest leaveRequest;

    public LeaveCalc() {
    }

    public LeaveCalc(UUID reqId, int paidLeaves, int unpaidLeaves, int balanceLeaves, int totalLeaves,
            LeaveRequest leaveRequest) {
        this.reqId = reqId;
        this.paidLeaves = paidLeaves;
        this.unpaidLeaves = unpaidLeaves;
        this.balanceLeaves = balanceLeaves;
        this.totalLeaves = totalLeaves;
        this.leaveRequest = leaveRequest;
    }

    public UUID getReqId() {
        return reqId;
    }

    public void setReqId(UUID reqId) {
        this.reqId = reqId;
    }

    public int getPaidLeaves() {
        return paidLeaves;
    }

    public void setPaidLeaves(int paidLeaves) {
        this.paidLeaves = paidLeaves;
    }

    public int getUnpaidLeaves() {
        return unpaidLeaves;
    }

    public void setUnpaidLeaves(int unpaidLeaves) {
        this.unpaidLeaves = unpaidLeaves;
    }

    public int getBalanceLeaves() {
        return balanceLeaves;
    }

    public void setBalanceLeaves(int balanceLeaves) {
        this.balanceLeaves = balanceLeaves;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(int total_leaves) {
        this.totalLeaves = total_leaves;
    }

    public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    

}
