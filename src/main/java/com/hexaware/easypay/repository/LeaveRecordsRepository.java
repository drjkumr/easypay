package com.hexaware.easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.easypay.entity.LeaveRecords;

public interface LeaveRecordsRepository extends JpaRepository<LeaveRecords, Long>{

    //JPQL

@Query("SELECT COUNT(lr) FROM LeaveRecords lr WHERE lr.empId = :empId AND MONTH(lr.leaveDate) = :month AND YEAR(lr.leaveDate) = :year")
int countUnpaidLeavesForMonth(@Param("empId") Long empId, @Param("month") int month, @Param("year") int year);

}
