package com.hexaware.easypay.service;

import java.util.Map;
import java.util.UUID;

import com.hexaware.easypay.dto.LeaveCalcDTO;

public interface ILeaveService {

    public void approveLeaveRequest(UUID reqId, LeaveCalcDTO dto1);

    public void rejectLeaveRequest(UUID reqId);

    public Map<String,Integer> getTotalLeaveDaysbyUUID(UUID reqId);

}
