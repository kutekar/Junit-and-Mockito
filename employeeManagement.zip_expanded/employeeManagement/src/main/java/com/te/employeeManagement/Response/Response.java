package com.te.employeeManagement.Response;

import com.te.employeeManagement.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response{
 
	private boolean error;
	private String message;
	private Object data;

}
