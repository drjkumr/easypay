package com.hexaware.easypay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

<<<<<<< HEAD
@ExceptionHandler(EmployeeNotFoundException.class)
public ResponseEntity<Object> handleEmployeeNotFound(EmployeeNotFoundException ex) {
    record InlineError(String message, int status) {}

    InlineError error = new InlineError(
        ex.getMessage(),
        HttpStatus.NOT_FOUND.value()
    );

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}

@ExceptionHandler
public ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex) {

    record InlineError(String message, int status) {}

    InlineError error = new InlineError(
        ex.getMessage(),
        HttpStatus.NOT_FOUND.value()
    );

       return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
=======
>>>>>>> 314dc4c (Updated latest backend)

@ExceptionHandler
public ResponseEntity<Object> handlePasswordMismatch(PasswordMismatchException ex) {

    record InlineError(String message, int status) {}

    InlineError error = new InlineError(
        ex.getMessage(),
        HttpStatus.FORBIDDEN.value()
    );

       return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
}

@ExceptionHandler
<<<<<<< HEAD
public ResponseEntity<Object> handleDefaultNotFound(DefaultException ex) {
=======
public ResponseEntity<Object> handleDefaultException(DefaultException ex) {
>>>>>>> 314dc4c (Updated latest backend)

    record InlineError(String message, int status) {}

    InlineError error = new InlineError(
        ex.getMessage(),
        HttpStatus.NOT_FOUND.value()
    );

       return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}


<<<<<<< HEAD
}
=======
}
>>>>>>> 314dc4c (Updated latest backend)
