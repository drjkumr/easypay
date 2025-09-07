package com.hexaware.easypay.repository;

<<<<<<< HEAD
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.LeaveCalc;

public interface LeaveCalcRepository extends  JpaRepository<LeaveCalc, UUID>{
=======
import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.easypay.entity.LeaveCalc;

public interface LeaveCalcRepository extends JpaRepository<LeaveCalc, Long>{
>>>>>>> 314dc4c (Updated latest backend)

}
