package com.hexaware.easypay.service;

import java.util.List;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.dto.SalaryPolicyDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.SalaryPolicy;

public interface IAdminService {

    public String createEmployee(EmployeeDTO dto);
    public String updateEmployee(Long empId, EmployeeDTO dto) ;
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(Long empId);
    public List<Employee> getEmployeeByName(String name);
    public List<Employee> getEmployeeByDept(String dept);
    public Employee getEmployeeByEmail(String email);
    public String deleteEmployeeById(Long empId);
    public String purgeAllEmployees();
    public String defineSalaryPolicy(SalaryPolicyDTO dto);
    public String modifySalaryPolicy(Long policyId, SalaryPolicyDTO dto);
    public byte[] generateCompliancePdf(Long policyId);
    public List<SalaryPolicy> getAllPolicies();
    public String deletePolicy(Long policyId);
    public String activateDeactivatePayroll(Long empId, PayrollDTO dto) ;
    public SalaryPolicy getPolicyById(Long policyId);









}
