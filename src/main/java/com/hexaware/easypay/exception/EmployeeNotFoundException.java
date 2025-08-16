package com.hexaware.easypay.exception;

public class EmployeeNotFoundException extends  RuntimeException {

    public EmployeeNotFoundException(int empId) {
        super("Employee not found with ID: " + empId);
    }

        public EmployeeNotFoundException(String dept) {
        super("Employee not found with Department: " + dept);
    }

}
