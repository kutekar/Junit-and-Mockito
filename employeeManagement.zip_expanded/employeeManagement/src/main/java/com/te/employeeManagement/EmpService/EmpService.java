package com.te.employeeManagement.EmpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.employeeManagement.EmpRepositary.EmpRepo;
import com.te.employeeManagement.Wrapper.Wrapper;
import com.te.employeeManagement.entity.Employee;
import com.te.employeeManagement.entity.EmployeeDetails;

public interface EmpService {


	

	public void delete( int empId);

	public List<Employee> FindAll();

	


	public Employee save(Employee employee);

	Employee update(Employee employee);

	


public Wrapper Add(Wrapper wrapper);

public Employee findById(int empId);
	

	

	

 

}
