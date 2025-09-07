package com.hexaware.easypay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hexaware.easypay.dto.EmpLeaveRequestDTO;
import com.hexaware.easypay.dto.EmpTimesheetDTO;
import com.hexaware.easypay.dto.EmployeeDetailsWrapper;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Payslip;
import com.hexaware.easypay.entity.Timesheet;
import com.hexaware.easypay.service.IEmployeeService;

@RestController
@RequestMapping("/api/employee")
// @CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/getall-payslips/{empId}") // ✅
    public List<Payslip> getAllPayslips(@PathVariable Long empId) {
        return employeeService.getAllPayslips(empId);
    }

    @GetMapping("/get-payslip/{payslipId}")// ✅
    public Payslip getPayslipById(@PathVariable Long payslipId) {
        return employeeService.getPayslipbyId(payslipId);
    }

    @PostMapping("/leave-request/{empId}") // ✅
    public String sendLeaveRequest(@PathVariable Long empId, @RequestBody EmpLeaveRequestDTO dto) {
        return employeeService.sendLeaveRequest(empId, dto);
    }

    @GetMapping("/getall-leave-requests/{empId}")  // ✅
    public List<LeaveRequest> getAllLeaveRequests(@PathVariable Long empId) {
        return employeeService.getAllLeaveRequests(empId);
    }

    @GetMapping("/get-leave-request/{reqId}") // ✅
    public LeaveRequest getLeaveRequestById(@PathVariable Long reqId) {
        return employeeService.getLeaveRequestById(reqId);
    }

    @GetMapping("/latest-leave-request/{empId}") // ✅
    public Optional<LeaveRequest> getLatestLeaveRequest(@PathVariable Long empId) {
        return employeeService.getLatestLeaveRequest(empId);
    }

    @PutMapping("/change-details/{empId}")// ✅
    public String changeEmployeeDetails(@PathVariable Long empId, @RequestBody EmployeeDetailsWrapper dto) {
        return employeeService.changeEmployeeDetails(empId, dto.getDetails(), dto.getPassword());
    }

    @GetMapping("/get-details/{empId}")// ✅
    public Employee getEmployeeDetails(@PathVariable Long empId) {
        return employeeService.getEmployeeDetails(empId);
    }

    @PostMapping("/submit-timesheet/{empId}") // ✅
    public String submitTimesheet(@PathVariable Long empId, @RequestBody EmpTimesheetDTO dto) {
        return employeeService.submitTimesheet(empId, dto);
    }

    @GetMapping("/getall-timesheets/{empId}")  // ✅
    public List<Timesheet> getAllTimesheets(@PathVariable Long empId) {
        return employeeService.getAllTimesheets(empId);
    }

    @GetMapping("/get-timesheet/{timesheetId}")  // ✅
    public Timesheet getTimesheetById(@PathVariable Long timesheetId) {
        return employeeService.getTimesheetById(timesheetId);
    }

    @GetMapping("/get-id/{email}")
    public Long getEmployeeIDfromEmail(@PathVariable String email) {
        return employeeService.getEmployeeIDfromEmail(email);
    }

     @GetMapping("/generate-payslip/{empId}")
    public ResponseEntity<byte[]> generatePayslip(@PathVariable Long empId) {
        byte[] pdf = employeeService.generatePayslipPdf(empId);

        return ResponseEntity.ok()
            .header("Content-Type", "application/pdf")
            .header("Content-Disposition", "attachment; filename=payslip_" + empId + ".pdf")
            .body(pdf);
    }

}
