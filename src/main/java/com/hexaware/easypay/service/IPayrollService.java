package com.hexaware.easypay.service;

import java.util.Map;
import java.util.UUID;

import com.hexaware.easypay.dto.PerformanceDTO;

public interface IPayrollService {

    public void calculateBonus(int empId, PerformanceDTO dto);

    public void calculateSalary(int empId);

    public Map<String, UUID> generatePaymentAck(int empId);

}
