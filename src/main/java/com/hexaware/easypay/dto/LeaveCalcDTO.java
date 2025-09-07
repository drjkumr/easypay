package com.hexaware.easypay.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class LeaveCalcDTO {

    @Pattern(regexp = "^[+-]?\\d+$", message = "Must be a valid signed reqId")
    @NotNull
    private int reqId;
    @Pattern(regexp = "^[+-]?\\d+$", message = "Must be a valid signed integer")
    private int paidLeaves;
    @Pattern(regexp = "^[+-]?\\d+$", message = "Must be a valid signed integer")
    private int unpaidLeaves;
    @Pattern(regexp = "^[+-]?\\d+$", message = "Must be a valid signed integer")
    private int balanceLeaves;
    @Pattern(regexp = "^[+-]?\\d+$", message = "Must be a valid signed integer")
    private int totalLeaves;

    public LeaveCalcDTO() {
    }

    public LeaveCalcDTO(int reqId, int paidLeaves, int unpaidLeaves, int balanceLeaves, int totalLeaves) {
        this.reqId = reqId;
        this.paidLeaves = paidLeaves;
        this.unpaidLeaves = unpaidLeaves;
        this.balanceLeaves = balanceLeaves;
        this.totalLeaves = totalLeaves;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
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

    public void setTotalLeaves(int totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    
=======
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeaveCalcDTO {

    private Long reqId;
    private Integer totalDays;
    private Integer paidLeaves;
    private Integer unpaidLeaves;

>>>>>>> 314dc4c (Updated latest backend)
}
