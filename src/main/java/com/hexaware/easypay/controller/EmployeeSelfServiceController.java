package com.hexaware.easypay.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.PaymentAcknowledgement;
import com.hexaware.easypay.service.IEmployeeSpecificService;

//For Employee access only!

@RestController
@RequestMapping("/api/employee")
public class EmployeeSelfServiceController {

    private final IEmployeeSpecificService employeeSpecificService;

    //constructor injection
    public EmployeeSelfServiceController(IEmployeeSpecificService employeeSpecificService) {
        this.employeeSpecificService = employeeSpecificService;
    }

    //Submit leave requests to be reviewd (Partially fills table)
    @PostMapping("/{empId}/leaves")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Map<String, UUID> submitLeaveRequest(
            @PathVariable int empId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return employeeSpecificService.submitLeaveRequest(empId, start, end);
    }

    //View leave requests
    @GetMapping("/leaves/{reqId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public LeaveRequest viewLeaveRequest(@PathVariable UUID reqId) {
        return employeeSpecificService.viewLeaveRequest(reqId);
    }

    //Check payment acknowledgement - Deliberate design choice later integration for mailto:user <acknowledgement-ID> to access the paymentAcknowledgement
    @GetMapping("/payment-ack/{ackId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public PaymentAcknowledgement viewPaymentAcknowledgement(@PathVariable int empId, @PathVariable UUID ackId) {
        return employeeSpecificService.viewPaymentAcknowledgement(ackId, empId);
    }
}
