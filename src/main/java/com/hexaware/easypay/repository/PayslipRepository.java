package com.hexaware.easypay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.Payslip;

public interface PayslipRepository extends JpaRepository<Payslip, Long> {

    Optional<Payslip> findTopByEmpIdOrderByPayslipIdDesc(Long empId);
    List<Payslip> findAllByEmpId(Long empId);

}
