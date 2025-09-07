package com.hexaware.easypay.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.dto.SalaryPolicyDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.entity.SalaryPolicy;
import com.hexaware.easypay.exception.DefaultException;
import com.hexaware.easypay.repository.EmployeeRepository;
import com.hexaware.easypay.repository.PayrollRepository;
import com.hexaware.easypay.repository.SalaryPolicyRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements  IAdminService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryPolicyRepository salaryPolicyRepository;

    @Autowired
    private ComplianceReporting complianceReporting;

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private PasswordEncoder encoder;

    

    // CRUD Services

    @Transactional
    @Override
    public String createEmployee(EmployeeDTO dto) {

        Employee employee = new Employee();
        Payroll payroll = new Payroll();

        employee.setName(dto.getName());
        employee.setDept(dto.getDept());
        employee.setEmail(dto.getEmail());
        employee.setPassword(encoder.encode(dto.getPassword()));
        employee.setRole(dto.getRole());
        employee.setCtc(dto.getCtc());
        employee.setDisbursedSalary(dto.getDisbursedSalary());
        employee.setPaidLeaves(dto.getPaidLeaves());

       Long policyId = dto.getPolicyId();

        SalaryPolicy policy = salaryPolicyRepository.findById(policyId).orElseThrow(() -> new DefaultException("Policy not found, create or enter correct policy to resolve issue"));

        payroll.setPolicyId(policy.getPolicyId()); 

       Employee savedEmployee =  employeeRepository.save(employee);

         payroll.setEmployee(savedEmployee);
        payroll.setActive(true);
        

        payrollRepository.save(payroll);

        return "Employee created successfully with ID: " + savedEmployee.getEmpId();
    }

    @Transactional
    @Override
    public String updateEmployee(Long empId, EmployeeDTO dto) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new DefaultException("Employee not found!"));
          Payroll payroll = payrollRepository.findById(empId).orElseThrow(() -> new DefaultException("Payroll not enrolled!"));

        employee.setName(dto.getName());
        employee.setDept(dto.getDept());
        employee.setEmail(dto.getEmail());
        employee.setPassword(encoder.encode(dto.getPassword()));
        employee.setRole(dto.getRole());
        employee.setCtc(dto.getCtc());
        employee.setDisbursedSalary(dto.getDisbursedSalary());
        employee.setPaidLeaves(dto.getPaidLeaves());

        Long policyId = dto.getPolicyId();

        SalaryPolicy policy = salaryPolicyRepository.findById(policyId).orElseThrow(() -> new DefaultException("Policy not found, create or enter correct policy to resolve issue"));

        payroll.setPolicyId(policy.getPolicyId()); 



        employeeRepository.save(employee);

        return "Employee updated successfully!";
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new DefaultException("Employee not found!"));
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Employee> getEmployeeByDept(String dept) {
        return  employeeRepository.findByDept(dept);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public String deleteEmployeeById(Long empId) {
        employeeRepository.findById(empId).orElseThrow(() -> new DefaultException("Employee not found!"));
        employeeRepository.deleteById(empId);
        return "Employee Deleted successfully ";
    }

    @Transactional
    @Override
    public String purgeAllEmployees() {
        employeeRepository.deleteAll();
        return "Employees have been purged and database cleared. Action cannot be reversed.";
    }

    //Salary Policies

    @Transactional
    @Override
    public String defineSalaryPolicy(SalaryPolicyDTO dto) {
        
        SalaryPolicy salaryPolicy = new SalaryPolicy();

        salaryPolicy.setPolicyId(dto.getPolicyId());
        salaryPolicy.setBasePercent(dto.getBasePercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setHraPercent(dto.getHraPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setTaPercent(dto.getTaPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setOaPercent(dto.getOaPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setPfPercent(dto.getPfPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setIncomeTaxPercent(dto.getIncomeTaxPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setProfTaxFixed(dto.getProfTaxFixed());
        salaryPolicy.setPolicyDesc(dto.getPolicyDesc());

        SalaryPolicy policy = salaryPolicyRepository.save(salaryPolicy);

        

        return "Salary policy defined successfully with Policy ID: " + policy.getPolicyId();
    }

    @Transactional
    @Override
    public String modifySalaryPolicy(Long policyId, SalaryPolicyDTO dto) {

        SalaryPolicy salaryPolicy = salaryPolicyRepository.findById(policyId).orElseThrow(() -> new DefaultException("Policy not defined!"));

         salaryPolicy.setBasePercent(dto.getBasePercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setHraPercent(dto.getHraPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setTaPercent(dto.getTaPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setOaPercent(dto.getOaPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setPfPercent(dto.getPfPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setIncomeTaxPercent(dto.getIncomeTaxPercent().divide(BigDecimal.valueOf(100)));
        salaryPolicy.setProfTaxFixed(dto.getProfTaxFixed());
        salaryPolicy.setPolicyDesc(dto.getPolicyDesc());

        SalaryPolicy policy = salaryPolicyRepository.save(salaryPolicy);

        return "Salary policy updated successfully with Policy ID: " + policy.getPolicyId();
    }

    //Compliance Reporting
    @Override
    public byte[] generateCompliancePdf(Long policyId) {
       
        SalaryPolicy policy = salaryPolicyRepository.findById(policyId).orElseThrow(() -> new DefaultException("Policy not found!"));
        return complianceReporting.generateComplianceReport(policyId, policy);
}

    @Override
    public List<SalaryPolicy> getAllPolicies() {
        return  salaryPolicyRepository.findAll();
    }

    @Transactional
    @Override
    public SalaryPolicy getPolicyById(Long policyId) {
        return salaryPolicyRepository.findById(policyId).orElseThrow(() -> new DefaultException("Policy not found!"));
    }

    @Transactional
    @Override
    public String deletePolicy(Long policyId) {
        salaryPolicyRepository.deleteById(policyId);
        return "Policy successfully deleted";
    }

    @Transactional
    @Override
    public String activateDeactivatePayroll(Long empId, PayrollDTO dto) {

        Payroll payroll = payrollRepository.findById(empId).orElseThrow(() -> new DefaultException("Employee not enrolled in payroll!"));

        payroll.setActive(dto.getActive());
        payroll.setPolicyId(dto.getPolicyId());

       Payroll payrollSave = payrollRepository.save(payroll);

       Boolean activation = payrollSave.getActive();

     String status = activation ? "activated" : "deactivated";

        return "Employee was " + status + " successfully!";

    }






}
