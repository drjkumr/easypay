package com.hexaware.easypay.controller;

<<<<<<< HEAD
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
=======
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.PayslipMonthYearDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.PayoutCalc;
import com.hexaware.easypay.service.IAdminService;
import com.hexaware.easypay.service.IPayrollService;

@RestController
@RequestMapping("/api/payroll")
// @CrossOrigin("http://localhost:5173")
public class PayrollController {

    @Autowired
    private IPayrollService payrollService;

    @Autowired
    private IAdminService adminService;

    @PostMapping("/calculate-payout/{empId}") // ✅
    public String CalculatePayout(@PathVariable  Long empId) {
        return payrollService.calculatePayout(empId);
    }

    @GetMapping("/view-pcbyid/{empId}") // ✅
    public List<PayoutCalc> ViewPaymentComponentsByEmpId(@PathVariable Long empId) {
        return payrollService.viewPaymentComponentsByEmpId(empId);
    }

    @GetMapping("/view-latest-pcbyid/{empId}") // ✅
    public Optional<PayoutCalc> ViewLatestPaymentComponentByEmpId(@PathVariable Long empId) {
        return payrollService.viewLatestPaymentComponentbyEmpId(empId);
    }

    @PostMapping("/calculate-payslip/{payoutId}")  // ✅
    public String CalculatePayslip(@PathVariable Long payoutId, @RequestBody PayslipMonthYearDTO dto) {
        return payrollService.calculatePayslip(payoutId, dto);
    }

        @GetMapping("/getall") // ✅
    public List<Employee> getAllEmployees() {
        return adminService.getAllEmployees();
    }

    @GetMapping("/get-id/{id}") // ✅
    public Employee getEmployeeById(@PathVariable Long id) {
        return adminService.getEmployeeById(id);
    }




>>>>>>> 314dc4c (Updated latest backend)
}
