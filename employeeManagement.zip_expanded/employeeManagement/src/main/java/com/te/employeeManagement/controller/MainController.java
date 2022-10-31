package com.te.employeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.employeeManagement.EmpService.EmpService;
import com.te.employeeManagement.EmpServiceImp.EmpServiceImp;
import com.te.employeeManagement.EmployeeDto.Employeedto;
import com.te.employeeManagement.Response.Response;
import com.te.employeeManagement.Wrapper.Wrapper;
import com.te.employeeManagement.entity.Employee;
import com.te.employeeManagement.entity.EmployeeDetails;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@RestController

public class MainController {
	@Autowired
	EmpService empservice;
     
	@PostMapping("saveEmployee")
	public ResponseEntity<Response> save(@RequestBody Employee employee) {
		Employee save = empservice.save(employee );
		
		return new ResponseEntity<>(new Response(false, "Data save successfully", save), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Response> get(@PathVariable int id) {
		Employee findById = empservice.findById(id);
		
		return new ResponseEntity<>(new Response(false, "fetching the data successfully", findById),
				HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable int id) {
		empservice.delete(id);
		
		return new ResponseEntity<>(new Response(false, "Data deleted successfully", id), HttpStatus.OK);
	}

	@GetMapping("findAll")
	public ResponseEntity<Response> findall() {
		List<Employee> findAll = empservice.FindAll();
		if (findAll.isEmpty()) {
			return new ResponseEntity<>(new Response(true, "date not found", findAll), HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(new Response(false, "data is present", findAll), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestBody Employee employee) {
		Employee update = empservice.update(employee);
		
		return new ResponseEntity<>(new Response(false, "Data updated", update), HttpStatus.OK);
	}
	
	
	
	@PostMapping("add")
	public ResponseEntity<Response> wrapper(@RequestBody Wrapper wrapper) {
		 Wrapper add = empservice.Add(wrapper);
		 return new ResponseEntity<>(new Response(false, "Data Added succefully",add ), HttpStatus.OK);
	}

}
	
	
	


