package com.hexaware.easypay.repository;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 314dc4c (Updated latest backend)
import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.Employee;

<<<<<<< HEAD
import java.util.List;


public interface EmployeeRepository extends  JpaRepository<Employee, Integer>{

    List<Employee> findByDept(String dept);
    List<Employee> findByDesignation(String designation);
=======
public interface EmployeeRepository  extends  JpaRepository<Employee, Long>{

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDept(String dept);

    Employee findByEmail(String email);
>>>>>>> 314dc4c (Updated latest backend)

}
