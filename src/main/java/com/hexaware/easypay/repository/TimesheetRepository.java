package com.hexaware.easypay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long>{

    List<Timesheet> findAllByEmpId(Long empId);
    Optional<Timesheet> findTopByEmpIdOrderByTimesheetIdDesc(Long empId);

}
