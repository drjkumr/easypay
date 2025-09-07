package com.hexaware.easypay.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.TimesheetApprovalDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveCalc;
import com.hexaware.easypay.entity.LeaveRecords;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Timesheet;
import com.hexaware.easypay.exception.DefaultException;
import com.hexaware.easypay.repository.EmployeeRepository;
import com.hexaware.easypay.repository.LeaveCalcRepository;
import com.hexaware.easypay.repository.LeaveRecordsRepository;
import com.hexaware.easypay.repository.LeaveRequestRepository;
import com.hexaware.easypay.repository.TimesheetRepository;

import jakarta.transaction.Transactional;

@Service
public class ManagerServiceImpl implements  IManagerService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private LeaveRecordsRepository leaveRecordsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveCalcRepository leaveCalcRepository;

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAllByOrderByReqIdDesc();
    }

    @Override
    public LeaveRequest getLeaveRequestByReqId(Long reqId) {
        return leaveRequestRepository.findById(reqId).orElseThrow(() -> new DefaultException("Request ID not found!"));
    }

    @Override
     public List<LeaveRequest> getLeaveRequestByEmpId(Long empId) {
        return leaveRequestRepository.findAllByEmpId(empId);
    }

    @Override
    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    @Override
    public Timesheet getTimesheetById(Long timesheetId) {
        return timesheetRepository.findById(timesheetId).orElseThrow(() -> new DefaultException("Timesheet ID not found!"));
    }

    @Transactional
    @Override
    public String approveDisapproveTimesheet(Long timesheetId, TimesheetApprovalDTO dto) {

        Timesheet timesheet = timesheetRepository.findById(timesheetId).orElseThrow(() -> new DefaultException( "Timesheet ID is not valid!"));

        timesheet.setApproval(dto.getApproval());
        Timesheet savedTimesheet = timesheetRepository.save(timesheet);

        return "Timesheet posted! with status " + savedTimesheet.getApproval();
    }

    
    @Override
    @Transactional
    public String DisapproveLeaveRequest(Long reqId) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(reqId).orElseThrow(() -> new DefaultException( "Timesheet ID is not valid!")); 
        
        if (Boolean.TRUE.equals(leaveRequest.getApproval())) {
        throw new DefaultException("Cannot disapprove an already approved request");
    }

        leaveRequest.setApproval(false);
        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        return "Rejection successful for request ID: " + savedLeaveRequest.getReqId(); 

    }

    @Transactional
    @Override
    public String ApproveLeaveRequest(Long reqId) {
 LeaveRequest leaveRequest = leaveRequestRepository.findById(reqId)
        .orElseThrow(() -> new DefaultException("Leave request not found"));

    Employee employee = employeeRepository.findById(leaveRequest.getEmpId())
        .orElseThrow(() -> new DefaultException("Employee not found"));

    leaveRequest.setApproval(true);
    leaveRequestRepository.save(leaveRequest);

    List<LocalDate> eligibleDates = new ArrayList<>();
    LocalDate current = leaveRequest.getStartDate();
    LocalDate end = leaveRequest.getEndDate();

    //Assuming Work days are 5 days and no public holidays (Can add apis to check for public holidays)
    while (!current.isAfter(end)) {
    DayOfWeek day = current.getDayOfWeek();
    if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
        eligibleDates.add(current);
    }
    current = current.plusDays(1);
}

    int unpaidCount = 0;
    int paidCount = 0;

    for (LocalDate date : eligibleDates) {
        if (employee.getPaidLeaves() > 0) {
            employee.setPaidLeaves(employee.getPaidLeaves() - 1);
            paidCount++;
            
        } else {
            LeaveRecords record = new LeaveRecords();
            record.setEmpId(employee.getEmpId());
            record.setLeaveDate(date);
            record.setCreatedAt(LocalDateTime.now());
            leaveRecordsRepository.save(record);

            unpaidCount++;
        }
    }


    LeaveCalc calc = new LeaveCalc();
    calc.setLeaveRequest(leaveRequest);
    calc.setTotalDays(eligibleDates.size());
    calc.setPaidLeaves(paidCount);
    calc.setUnpaidLeaves(unpaidCount);
    leaveCalcRepository.save(calc);

    return "Leave request approved. Paid leaves used: " + paidCount +
           ", unpaid recorded: " + unpaidCount +
           ", total days: " + eligibleDates.size();



    }

}
