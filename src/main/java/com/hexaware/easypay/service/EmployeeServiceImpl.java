package com.hexaware.easypay.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.DetailsChangeDTO;
import com.hexaware.easypay.dto.DetailsChangePswDTO;
import com.hexaware.easypay.dto.EmpLeaveRequestDTO;
import com.hexaware.easypay.dto.EmpTimesheetDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.PayoutCalc;
import com.hexaware.easypay.entity.Payslip;
import com.hexaware.easypay.entity.Timesheet;
import com.hexaware.easypay.exception.DefaultException;
import com.hexaware.easypay.repository.EmployeeRepository;
import com.hexaware.easypay.repository.LeaveRequestRepository;
import com.hexaware.easypay.repository.PayoutCalcRepository;
import com.hexaware.easypay.repository.PayslipRepository;
import com.hexaware.easypay.repository.TimesheetRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private PayslipPdfService payslipPdfService;

    @Autowired
    private PayoutCalcRepository payoutCalcRepository;

    @Override
    public Payslip getPayslipbyId(Long payslipId) {
        return payslipRepository.findById(payslipId)
                .orElseThrow(() -> new DefaultException("Requested payslip does not exist"));
    }

    @Override
    public List<Payslip> getAllPayslips(Long empId) {
        return payslipRepository.findAllByEmpId(empId);
    }

    @Transactional
    @Override
    public String sendLeaveRequest(Long empId, EmpLeaveRequestDTO dto) {

        LeaveRequest leaveRequest = new LeaveRequest();

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new DefaultException("Employee not found"));

        leaveRequest.setEmpId(employee.getEmpId());
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());

        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new DefaultException("Start date should be before End Date");
        }

        if(dto.getStartDate().isBefore(LocalDate.now())) {
            throw new DefaultException("Date can't be in the past");
        }

        leaveRequest.setReason(dto.getReason());

        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        return "Leave request with id " + savedLeaveRequest.getReqId() + " sent successfully!";
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests(Long empId) {
        return leaveRequestRepository.findAllByEmpId(empId);
    }

    @Override
    public LeaveRequest getLeaveRequestById(Long reqId) {
        return leaveRequestRepository.findById(reqId)
                .orElseThrow(() -> new DefaultException("Leave request not found"));
    }

    @Override
    public Optional<LeaveRequest> getLatestLeaveRequest(Long empId) {
        return leaveRequestRepository.findTopByEmpIdOrderByReqIdDesc(empId);
    }

    @Transactional
    @Override
    public String changeEmployeeDetails(Long empId, DetailsChangeDTO dto, DetailsChangePswDTO dto1) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new DefaultException("Employee might have been deleted in session!"));

        employee.setName(dto.getName());
        employee.setDept(dto.getDept());
        employee.setEmail(dto.getEmail());

        if (dto1 != null && dto1.getPassword() != null && !dto1.getPassword().isBlank()) {
            employee.setPassword(encoder.encode(dto1.getPassword()));
        }

        employeeRepository.save(employee);

        return "Employee changed successfully!";
    }

    @Override
    public Employee getEmployeeDetails(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new DefaultException("Employee record could not be fetched. Contact admin!"));
    }

    @Transactional
    @Override
    public String submitTimesheet(Long empId, EmpTimesheetDTO dto) {

        Timesheet timesheet = new Timesheet();

        timesheet.setEmpId(empId);
        timesheet.setNoDaysWorked(dto.getNoDaysWorked());

        Timesheet savedTimesheet = timesheetRepository.save(timesheet);

        return "Timesheet submitted successfully with ID: " + savedTimesheet.getTimesheetId();

    }

    @Override
    public List<Timesheet> getAllTimesheets(Long empId) {
        return timesheetRepository.findAllByEmpId(empId);
    }

    @Override
    public Optional<Timesheet> getLatestTimesheet(Long empId) {
        return timesheetRepository.findTopByEmpIdOrderByTimesheetIdDesc(empId);
    }

    @Override
    public Timesheet getTimesheetById(Long timesheetId) {
        return timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new DefaultException("Time sheet ID not found!"));
    }

    @Override
    public Long getEmployeeIDfromEmail(String email) {
        return employeeRepository.findByEmail(email).getEmpId();
    }

    @Override
    public byte[] generatePayslipPdf(Long empId) {

        PayoutCalc payout = payoutCalcRepository.findTopByEmpIdOrderByPayoutIdDesc(empId)
        .orElseThrow(() -> new DefaultException("PayoutCalc not found for empId: " + empId));

        Payslip payslip = payslipRepository.findTopByEmpIdOrderByPayslipIdDesc(empId)
                .orElseThrow(() -> new DefaultException("Payslip not found for empId: " + empId));

        return payslipPdfService.generatePayslip(payout, payslip);
    }

}
