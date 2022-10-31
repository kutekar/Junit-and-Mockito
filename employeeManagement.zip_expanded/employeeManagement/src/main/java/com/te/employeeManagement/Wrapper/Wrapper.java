package com.te.employeeManagement.Wrapper;

import com.te.employeeManagement.entity.Employee;
import com.te.employeeManagement.entity.EmployeeDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wrapper {
	private Employee employee;
	private EmployeeDetails employeeDetails;

}
