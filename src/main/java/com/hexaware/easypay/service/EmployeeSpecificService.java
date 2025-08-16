package com.hexaware.easypay.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.PaymentAcknowledgement;
import com.hexaware.easypay.exception.DefaultException;
import com.hexaware.easypay.repository.LeaveRequestRepository;
import com.hexaware.easypay.repository.PaymentAcknowledgementRepository;

import jakarta.transaction.Transactional;

//For employee use only!

@Service
public class EmployeeSpecificService implements  IEmployeeSpecificService{

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private PaymentAcknowledgementRepository paymentAcknowledgementRepository;

    @Transactional
    @Override
    public Map<String, UUID> submitLeaveRequest(int empId, LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            throw new DefaultException("Start date must be before end date.");
        }

        LeaveRequest lr = new LeaveRequest();

        // Generating acknowledgement ID
        UUID reqId = UUID.randomUUID();

        lr.setReqId(reqId);
        lr.setEmpId(empId);
        lr.setStartDate(start);
        lr.setEndDate(end);

        LeaveRequest leaveReqSaved = leaveRequestRepository.save(lr);

        return Map.of("Your generated request ID", leaveReqSaved.getReqId());
    }

    @Override
    public LeaveRequest viewLeaveRequest(UUID reqId) {
        return leaveRequestRepository.findById(reqId)
                .orElseThrow(() -> new DefaultException("Leave request is not found"));
    }

    @Override
    public PaymentAcknowledgement viewPaymentAcknowledgement(UUID ackId, int empId) {
        PaymentAcknowledgement ack = paymentAcknowledgementRepository.findById(ackId)
                .orElseThrow(() -> new DefaultException("Payment acknowledgement not found."));

        if (ack.getEmpId() != empId) {
            throw new DefaultException("Access denied: This acknowledgement does not belong to your employee ID.");
        }

        return ack;

    }

}
