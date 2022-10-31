package com.te.employeeManagement.EmpRepositary;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.te.employeeManagement.entity.EmployeeDetails;
@Repository
public interface EmpDetailsRepo extends MongoRepository<EmployeeDetails, Integer> {

}
