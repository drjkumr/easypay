package com.hexaware.easypay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.TimesheetApprovalDTO;
import com.hexaware.easypay.entity.Employee;
import  com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Timesheet;
import com.hexaware.easypay.service.IAdminService;
import com.hexaware.easypay.service.IManagerService;

@RestController
@RequestMapping("/api/manager")
// @CrossOrigin(origins = "http://localhost:5173")
public class ManagerController {

    @Autowired
    private IManagerService managerService;

    @Autowired
    private IAdminService adminService;

    @GetMapping("/getall-leave-requests") // ✅
    public List<LeaveRequest> getAllLeaveRequests() {
        return managerService.getAllLeaveRequests();
    }

    @GetMapping("/get-lr-id/{empId}") // ✅
    public List<LeaveRequest> getLeaveRequestByEmpId(@PathVariable Long empId) { 
        return managerService.getLeaveRequestByEmpId(empId);
    }

    @GetMapping("/get-lr-reqid/{reqId}")
    public LeaveRequest getLeaveRequestByReqId(@PathVariable Long reqId) {
        return managerService.getLeaveRequestByReqId(reqId);
    }

    @GetMapping("/getall-ts")// ✅
    public List<Timesheet> getAllTimesheets() {
        return managerService.getAllTimesheets();
    }

    @GetMapping("/get-ts-id/{timesheetId}")// ✅
    public Timesheet getTimesheetById(@PathVariable Long timesheetId) {
        return managerService.getTimesheetById(timesheetId);
    }

    @PutMapping("/approve-disapprove-ts/{timesheetId}")// ✅
    public String approveDisapproveTimesheet(@PathVariable Long timesheetId, @RequestBody TimesheetApprovalDTO dto) {
        return managerService.approveDisapproveTimesheet(timesheetId, dto);
    }

    @PutMapping("/disapprove-lr/{reqId}")// ✅
    public String DisapproveLeaveRequest(@PathVariable Long reqId) {
        return managerService.DisapproveLeaveRequest(reqId);
    }

    @PutMapping("/approve-lr/{reqId}")// ✅
    public String ApproveLeaveRequest(@PathVariable Long reqId) {
        return managerService.ApproveLeaveRequest(reqId);
    }

    @GetMapping("/getall") // ✅
    public List<Employee> getAllEmployees() {
        return adminService.getAllEmployees();
    }

    @GetMapping("/get-id/{id}") // ✅
    public Employee getEmployeeById(@PathVariable Long id) {
        return adminService.getEmployeeById(id);
    }



}
