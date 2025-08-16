package com.hexaware.easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.Employee;

import java.util.List;


public interface EmployeeRepository extends  JpaRepository<Employee, Integer>{

    List<Employee> findByDept(String dept);
    List<Employee> findByDesignation(String designation);

}
