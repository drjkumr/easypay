package com.hexaware.easypay.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.PaymentAcknowledgement;

public interface IEmployeeSpecificService {

    public Map<String, UUID> submitLeaveRequest(int empId, LocalDate start, LocalDate end);
    public LeaveRequest viewLeaveRequest(UUID reqId);
    public PaymentAcknowledgement viewPaymentAcknowledgement(UUID ackId, int empId);

}
