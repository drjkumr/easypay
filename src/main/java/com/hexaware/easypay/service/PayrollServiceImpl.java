package com.hexaware.easypay.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.PayslipMonthYearDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.PayoutCalc;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.entity.Payslip;
import com.hexaware.easypay.entity.SalaryPolicy;
import com.hexaware.easypay.exception.DefaultException;
import com.hexaware.easypay.repository.EmployeeRepository;
import com.hexaware.easypay.repository.LeaveRecordsRepository;
import com.hexaware.easypay.repository.PayoutCalcRepository;
import com.hexaware.easypay.repository.PayrollRepository;
import com.hexaware.easypay.repository.PayslipRepository;
import com.hexaware.easypay.repository.SalaryPolicyRepository;

import jakarta.transaction.Transactional;

@Service
public class PayrollServiceImpl implements  IPayrollService{

    @Autowired
    private  EmployeeRepository employeeRepository;

    @Autowired
    private SalaryPolicyRepository salaryPolicyRepository;

    @Autowired
    private PayoutCalcRepository payoutCalcRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private LeaveRecordsRepository leaveRecordsRepository;


    @Override
    @Transactional
    public String calculatePayout(Long empId) {

        PayoutCalc payoutCalc = new PayoutCalc();

        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new DefaultException("Requested employee not found!"));

        Payroll payroll = payrollRepository.findById(empId).orElseThrow(() -> new DefaultException("Requested employee not enrolled!"));

        if (payroll.getActive() == false || payroll.getActive() == null) {
            return  "Employee payroll is disabled!";
        } 

        Long policyId = payroll.getPolicyId();

        if (payroll.getPolicyId() == null) {
            return "Policy Id not set";
        }

        SalaryPolicy salaryPolicy = salaryPolicyRepository.findById(policyId).orElseThrow(() -> new DefaultException("Requested salary policy not found!, contact admin to resolve changes!"));

        BigDecimal ctc = employee.getCtc();

        payoutCalc.setEmpId(empId);
        payoutCalc.setBase(ctc.multiply(salaryPolicy.getBasePercent()));
        payoutCalc.setHra(ctc.multiply(salaryPolicy.getHraPercent()));
        payoutCalc.setTa(ctc.multiply(salaryPolicy.getTaPercent()));
        payoutCalc.setOa(ctc.multiply(salaryPolicy.getOaPercent()));
        payoutCalc.setPf(ctc.multiply(salaryPolicy.getPfPercent()));
        payoutCalc.setProfTax(salaryPolicy.getProfTaxFixed());
        payoutCalc.setIncomeTax(ctc.multiply(salaryPolicy.getIncomeTaxPercent()));

        PayoutCalc s1  = payoutCalcRepository.save(payoutCalc);

        BigDecimal gross = s1.getBase().add(s1.getHra()).add( s1.getTa()).add( s1.getOa()) .subtract( s1.getPf()).subtract( s1.getProfTax()).subtract( s1.getIncomeTax());

        //error here - resolved
        BigDecimal monthly = gross.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

        BigDecimal daily = gross.divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);

        payoutCalc.setGross(gross);
        payoutCalc.setMonthly(monthly);
        payoutCalc.setDaily(daily);

        PayoutCalc saved = payoutCalcRepository.save(payoutCalc);

        return "Salary components calculated for " + employee.getName() + "with ID: " + employee.getEmpId() + "with payout ID: " + saved.getPayoutId();

    }

    @Override
    public List<PayoutCalc> viewPaymentComponentsByEmpId(Long empId){
        return payoutCalcRepository.findByEmpId(empId);
       }

    @Override
    public Optional<PayoutCalc> viewLatestPaymentComponentbyEmpId(Long empId) {
        return payoutCalcRepository.findTopByEmpIdOrderByPayoutIdDesc(empId);
    }

    @Override
    @Transactional
    public String calculatePayslip(Long payoutId, PayslipMonthYearDTO dto) {

        PayoutCalc payoutCalc = payoutCalcRepository.findById(payoutId).orElseThrow(() -> new DefaultException("Requested payout ID does not exist!"));
         Employee employee = employeeRepository.findById(payoutCalc.getEmpId()).orElseThrow(() -> new DefaultException("Requested employee not found!"));
        Payslip payslip = new Payslip();

       Integer month = dto.getMonth();
        Integer year = dto.getYear();

        Integer unpaidDays = leaveRecordsRepository.countUnpaidLeavesForMonth(payoutCalc.getEmpId(), month, year);

        BigDecimal deduction = BigDecimal.valueOf(unpaidDays).multiply(payoutCalc.getDaily());

        BigDecimal actualMonthlyPayout = payoutCalc.getMonthly().subtract(deduction);

        payslip.setEmpId(payoutCalc.getEmpId());
        payslip.setActualMonthlyPayout(actualMonthlyPayout);
        payslip.setBenefits(BigDecimal.valueOf(1000));

        Payslip saved = payslipRepository.save(payslip);

        return "Payslip generated for " + employee.getName() + " with ID: " + employee.getEmpId() + " with payslip ID " + saved.getPayslipId() ;
    }





    

}


