package com.hexaware.easypay.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.LeaveRequest;


public interface LeaveRequestRepository extends  JpaRepository<LeaveRequest, UUID>{

    List<LeaveRequest> findByEmpId(int empId);
    Optional<LeaveRequest> findTopByEmpIdAndApprovalStatusOrderByApproveDateDesc(int empId, String status);

}
