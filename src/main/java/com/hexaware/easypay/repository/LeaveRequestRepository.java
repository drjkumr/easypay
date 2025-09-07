package com.hexaware.easypay.repository;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
import java.util.UUID;
=======
>>>>>>> 314dc4c (Updated latest backend)

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.LeaveRequest;

<<<<<<< HEAD

public interface LeaveRequestRepository extends  JpaRepository<LeaveRequest, UUID>{

    List<LeaveRequest> findByEmpId(int empId);
    Optional<LeaveRequest> findTopByEmpIdAndApprovalStatusOrderByApproveDateDesc(int empId, String status);
=======
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    Optional<LeaveRequest> findTopByEmpIdOrderByReqIdDesc(Long EmpId);
    List<LeaveRequest> findAllByEmpId(Long empId);
    List<LeaveRequest> findAllByOrderByReqIdDesc();
>>>>>>> 314dc4c (Updated latest backend)

}
