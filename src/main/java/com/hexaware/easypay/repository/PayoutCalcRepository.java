package com.hexaware.easypay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.PayoutCalc;


public interface PayoutCalcRepository extends JpaRepository<PayoutCalc, Long>{

    List<PayoutCalc> findByEmpId(Long empId);

    Optional<PayoutCalc> findTopByEmpIdOrderByPayoutIdDesc(Long empId);

}
