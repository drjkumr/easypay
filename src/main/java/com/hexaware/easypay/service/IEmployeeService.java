package com.hexaware.easypay.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.easypay.dto.DetailsChangeDTO;
import com.hexaware.easypay.dto.DetailsChangePswDTO;
import com.hexaware.easypay.dto.EmpLeaveRequestDTO;
import com.hexaware.easypay.dto.EmpTimesheetDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Payslip;
import com.hexaware.easypay.entity.Timesheet;

public interface IEmployeeService {

    public Payslip getPayslipbyId(Long payslipId);
    public List<Payslip> getAllPayslips(Long empId);
    public String sendLeaveRequest(Long empId,EmpLeaveRequestDTO dto);
    public List<LeaveRequest> getAllLeaveRequests(Long empId);
    public LeaveRequest getLeaveRequestById(Long reqId);
    public Optional<LeaveRequest> getLatestLeaveRequest(Long empId);
    public String changeEmployeeDetails(Long empId, DetailsChangeDTO dto, DetailsChangePswDTO dto1);
    public Employee getEmployeeDetails(Long empId);
    public String submitTimesheet(Long empId, EmpTimesheetDTO dto);
    public List<Timesheet> getAllTimesheets(Long empId);
    public Optional<Timesheet> getLatestTimesheet(Long empId);
    public Timesheet getTimesheetById(Long timesheetId);
    public Long getEmployeeIDfromEmail(String email);
    public byte[] generatePayslipPdf(Long empId);

    




}
