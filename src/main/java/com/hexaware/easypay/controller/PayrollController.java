package com.hexaware.easypay.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.easypay.dto.PerformanceDTO;
import com.hexaware.easypay.service.IPayrollService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final IPayrollService payrollService;

    //constructor injection
    public PayrollController(IPayrollService payrollService) {
        this.payrollService = payrollService;
    }

    //Calculate Bonus for employee - TBD 1st
    @PostMapping("/employees/{empId}/performance")
    @PreAuthorize("hasRole('PAYROLL_PROCESSOR')")
    public void calculateBonus(@PathVariable int empId, @Valid @RequestBody PerformanceDTO dto) {
        payrollService.calculateBonus(empId, dto);
    }

    //Calculate salary for employee only after calculating bonus
    @PostMapping("/employees/{empId}/calculate-salary")
    @PreAuthorize("hasRole('PAYROLL_PROCESSOR')")
    public void calculateSalary(@PathVariable int empId) {
        payrollService.calculateSalary(empId);
    }

    //Generate Payment acknowledgement with future extension to mail:to employee
    @PostMapping("/employees/{empId}/payment-ack")
    @PreAuthorize("hasRole('PAYROLL_PROCESSOR')")
    public Map<String, UUID> generatePaymentAck(@PathVariable int empId) {
        return payrollService.generatePaymentAck(empId);
    }
}
