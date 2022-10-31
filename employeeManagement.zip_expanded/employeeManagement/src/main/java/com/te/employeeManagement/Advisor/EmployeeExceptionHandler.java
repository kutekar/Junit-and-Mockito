package com.te.employeeManagement.Advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.employeeManagement.Exception.EmployeeNotFoundException;
import com.te.employeeManagement.Response.Response;

@RestControllerAdvice
public class EmployeeExceptionHandler {
	
@ExceptionHandler(EmployeeNotFoundException.class)
public ResponseEntity<Response>employeeNotHandler(EmployeeNotFoundException exception){
	return new ResponseEntity<>(new Response(true,exception.getMessage(),null),HttpStatus.BAD_REQUEST);
}
}
