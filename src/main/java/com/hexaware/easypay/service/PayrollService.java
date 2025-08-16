package com.hexaware.easypay.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.PerformanceDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveCalc;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.PaymentAcknowledgement;
import com.hexaware.easypay.entity.Performance;
import com.hexaware.easypay.entity.SalaryCalc;
import com.hexaware.easypay.entity.SalaryComp;
import com.hexaware.easypay.entity.SalaryPolicy;
import com.hexaware.easypay.exception.DefaultException;
import com.hexaware.easypay.exception.EmployeeNotFoundException;
import com.hexaware.easypay.repository.EmployeeRepository;
import com.hexaware.easypay.repository.LeaveCalcRepository;
import com.hexaware.easypay.repository.LeaveRequestRepository;
import com.hexaware.easypay.repository.PaymentAcknowledgementRepository;
import com.hexaware.easypay.repository.PerformanceRepository;
import com.hexaware.easypay.repository.SalaryCalcRepository;
import com.hexaware.easypay.repository.SalaryCompRepository;
import com.hexaware.easypay.repository.SalaryPolicyRepository;

import de.jollyday.HolidayCalendar; //JollyDay Imported for Holiday tracking and calculation, India unavailable so using USA
import de.jollyday.HolidayManager;
import jakarta.transaction.Transactional;

/*
 * -------------------------------------------------------------------
 *  Created by: Dheeraj
 *  Under the guidance of: Trainer Javeed Sir
 *  Organization: Hexaware
 * -------------------------------------------------------------------
 */

//For payroll use only!

@Service
public class PayrollService implements IPayrollService {

    @Autowired
    private SalaryCalcRepository salaryCalcRepository;

    @Autowired
    private SalaryCompRepository salaryCompRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryPolicyRepository salaryPolicyRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveCalcRepository leaveCalcRepository;

    @Autowired
    private PaymentAcknowledgementRepository paymentAcknowledgementRepository;

    // Helper method to calculate the Percentage with BigDecimal type
    private BigDecimal percentageCalc(BigDecimal amount, BigDecimal percent) {
        if (percent == null) {
            return BigDecimal.valueOf(0);
        }
        return amount.multiply(percent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

    }

    @Transactional
    @Override
    public void calculateBonus(int empId, PerformanceDTO dto) {

        Performance performance = performanceRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException(empId));
        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));

        BigDecimal ctc = emp.getCtc();
        performance.setPerformance(dto.getPerformance());
        performance.setBonusPercent(dto.getBonusPercent());
        BigDecimal bonus = percentageCalc(ctc, performance.getBonusPercent());
        performance.setBonus(bonus);

        performanceRepository.save(performance);

    }

    @Transactional
    @Override
    public void calculateSalary(int empId) {

        SalaryComp comp = new SalaryComp();
        SalaryCalc calc = new SalaryCalc();
        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
        Performance performance = performanceRepository.findById(empId)
                .orElseThrow(() -> new DefaultException("Please provide a valid Performance ID!"));
        BigDecimal ctc = emp.getCtc();

        final int DEFAULT_POLICY_ID = 1; // Only 1 policy for now

        SalaryPolicy policy = salaryPolicyRepository.findById(DEFAULT_POLICY_ID)
                .orElseThrow(() -> new DefaultException("Please provide a valid policy!"));

        // setting values for Salary Components
        comp.setEmpId(empId);
        comp.setCtc(ctc);
        comp.setBase(percentageCalc(ctc, policy.getBasePercent()));
        comp.setHra(percentageCalc(ctc, policy.getHraPercent()));
        comp.setTa(percentageCalc(ctc, policy.getTaPercent()));
        comp.setOa(percentageCalc(ctc, policy.getOaPercent()));
        comp.setPf(percentageCalc(ctc, policy.getPfPercent()));
        comp.setProfTax(policy.getProfTaxFixed());
        comp.setIncomeTax(percentageCalc(ctc, policy.getIncomeTaxPercent()));
        comp.setBonus(performance.getBonus());

        // saving salary components
        SalaryComp savedComp = salaryCompRepository.save(comp);

        // basically taking the latest date of approval (refer repo) for reqId (pk)
        LeaveRequest leaveRequest = leaveRequestRepository
                .findTopByEmpIdAndApprovalStatusOrderByApproveDateDesc(empId, "approved")
                .orElseThrow(() -> new DefaultException("LeaveCalc and LeaveRequest Fetch error, Internal error"));

        int unpaidLeaves = 0;

        // Leave request UUID (pk) fetching
        if (leaveRequest != null) {
            UUID reqId = leaveRequest.getReqId();
            LeaveCalc leaveCalc = leaveCalcRepository.findById(reqId).orElse(null);

            if (leaveCalc != null) {
                unpaidLeaves = leaveCalc.getUnpaidLeaves();
            }
        }

        LocalDate now = LocalDate.now();
        LocalDate start = now.withDayOfMonth(1);
        LocalDate end = now.withDayOfMonth(now.lengthOfMonth());

        int workingDays = 0;

        Properties props = new Properties();
        props.setProperty("calendar", HolidayCalendar.UNITED_STATES.name());
        HolidayManager holidayManager = HolidayManager.getInstance(props);

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek dow = date.getDayOfWeek();
            boolean isWeekend = dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
            boolean isHoliday = holidayManager.isHoliday(date);

            if (!isWeekend && !isHoliday) {
                workingDays++;
            }
        }

        int payableDays = workingDays - unpaidLeaves;

        if (payableDays < 0) {
            payableDays = 0;
        }

        BigDecimal annualIncome = savedComp.getBase()
                .add(savedComp.getHra())
                .add(savedComp.getTa())
                .add(savedComp.getOa())
                .add(savedComp.getBonus())
                .subtract(savedComp.getPf())
                .subtract(savedComp.getProfTax())
                .subtract(savedComp.getIncomeTax());

        BigDecimal perDaySalary = annualIncome.divide(BigDecimal.valueOf(workingDays), 2, RoundingMode.HALF_UP);
        BigDecimal actualMonthlyPay = perDaySalary.multiply(BigDecimal.valueOf(payableDays));
        BigDecimal leaveDeduction = perDaySalary.multiply(BigDecimal.valueOf(unpaidLeaves));

        calc.setEmpId(empId);
        calc.setAnnualIncome(annualIncome);
        calc.setDailyPayout(perDaySalary);
        calc.setLeaveDeductions(leaveDeduction);
        calc.setActualMonthlyPayout(actualMonthlyPay);
        calc.setPerformanceAdditions(savedComp.getBonus());

        salaryCalcRepository.save(calc);

    }

    @Transactional
    @Override
    public Map<String, UUID> generatePaymentAck(int empId) {

        UUID ackId = UUID.randomUUID();

        // Checking whether the acknowledgement ID already exists
        if (paymentAcknowledgementRepository.existsById(ackId)) {
            throw new DefaultException("Payment acknowledgement already exists");
        }

        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));

        SalaryComp comp = salaryCompRepository.findById(empId)
                .orElseThrow(() -> new DefaultException("SalaryComp not found for empId: " + empId));

        SalaryCalc calc = salaryCalcRepository.findById(empId)
                .orElseThrow(() -> new DefaultException("SalaryCalc not found for empId: " + empId));

        // inserting values into payment acknowledgement
        PaymentAcknowledgement acknowledgement = new PaymentAcknowledgement();

        // Generating
        acknowledgement.setAckId(ackId);
        acknowledgement.setActualMonthlyPayout(calc.getActualMonthlyPayout());
        acknowledgement.setAnnualIncome(calc.getAnnualIncome());
        acknowledgement.setBase(comp.getBase());
        acknowledgement.setCtc(comp.getCtc());
        acknowledgement.setHra(comp.getHra());
        acknowledgement.setIncomeTax(comp.getIncomeTax());
        acknowledgement.setName(emp.getName());
        acknowledgement.setOa(comp.getOa());
        acknowledgement.setPf(comp.getPf());
        acknowledgement.setProfTax(comp.getProfTax());
        acknowledgement.setTa(comp.getTa());
        acknowledgement.setEmpId(emp.getEmpId());
        acknowledgement.setBonus(comp.getBonus());

        PaymentAcknowledgement ack = paymentAcknowledgementRepository.save(acknowledgement);

        // Clearing records
        salaryCompRepository.deleteById(empId);
        salaryCalcRepository.deleteById(empId);

        return Map.of("Generated UUID", ack.getAckId());
    }

}
