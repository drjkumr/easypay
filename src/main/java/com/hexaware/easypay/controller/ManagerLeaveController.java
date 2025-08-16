package com.hexaware.easypay.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.easypay.dto.LeaveCalcDTO;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.service.LeaveService;

import jakarta.validation.Valid;

//For Manager access only!

@RestController
@RequestMapping("/api/manager/leaves")
public class ManagerLeaveController {

    private final LeaveService leaveService;

    //constructor injection
    public ManagerLeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    //Get all leaves by employee
    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasRole('MANAGER')")
    public List<LeaveRequest> getLeavesByEmployee(@PathVariable int empId) {
        return leaveService.getLeaveRequestsByEmployee(empId);
    }

    //approve the leave
    @PostMapping("/{reqId}/approve")
    @PreAuthorize("hasRole('MANAGER')")
    public void approve(@PathVariable UUID reqId, @Valid @RequestBody LeaveCalcDTO dto) {
        leaveService.approveLeaveRequest(reqId, dto);
    }

    //reject the leave
    @PostMapping("/{reqId}/reject")
    @PreAuthorize("hasRole('MANAGER')")
    public void reject(@PathVariable UUID reqId) {
        leaveService.rejectLeaveRequest(reqId);
    }

    //Utility to show the number of working days in between leave dates and also update total Leaves
    @GetMapping("/{reqId}/effective-days")
    @PreAuthorize("hasRole('MANAGER')")
    public Map<String, Integer> showEffectiveDays(@PathVariable UUID reqId) {
        return leaveService.getTotalLeaveDaysbyUUID(reqId);
    }
}
