package com.te.employeeManagement.EmployeeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employeedto {
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private double contactNo;
	private int age;
	private double salary;

}
