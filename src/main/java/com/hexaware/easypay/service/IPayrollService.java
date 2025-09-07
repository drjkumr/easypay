package com.hexaware.easypay.service;

<<<<<<< HEAD
import java.util.Map;
import java.util.UUID;

import com.hexaware.easypay.dto.PerformanceDTO;

public interface IPayrollService {

    public void calculateBonus(int empId, PerformanceDTO dto);

    public void calculateSalary(int empId);

    public Map<String, UUID> generatePaymentAck(int empId);
=======
import java.util.List;
import java.util.Optional;

import com.hexaware.easypay.dto.PayslipMonthYearDTO;
import com.hexaware.easypay.entity.PayoutCalc;

public interface IPayrollService {

    public String calculatePayout(Long empId);
    public List<PayoutCalc> viewPaymentComponentsByEmpId(Long empId);
    public Optional<PayoutCalc> viewLatestPaymentComponentbyEmpId(Long empId);
    public String calculatePayslip(Long payoutId, PayslipMonthYearDTO dto);
    
>>>>>>> 314dc4c (Updated latest backend)

}
