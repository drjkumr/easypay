package com.hexaware.easypay.service;

import java.util.List;

import com.hexaware.easypay.dto.TimesheetApprovalDTO;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Timesheet;

public interface IManagerService {

    public List<LeaveRequest> getAllLeaveRequests();
    public LeaveRequest getLeaveRequestByReqId(Long reqId);
    public List<Timesheet> getAllTimesheets();
    public Timesheet getTimesheetById(Long timesheetId);
    public String approveDisapproveTimesheet(Long timesheetId, TimesheetApprovalDTO dto);
    public String DisapproveLeaveRequest(Long reqId);
    public String ApproveLeaveRequest(Long reqId);
    public List<LeaveRequest> getLeaveRequestByEmpId(Long empId);





    

}
