package com.hexaware.easypay.exception;

public class UsernameNotFoundException extends RuntimeException {

        public UsernameNotFoundException(int empId) {
        super("Employee not found for user: " + empId);
    }

    public UsernameNotFoundException(String username) {
        super("Employee not found for user: " + username);
    }


}
