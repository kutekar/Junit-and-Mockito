package com.te.employeeManagement.EmpRepositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.employeeManagement.entity.Employee;
@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {

}
