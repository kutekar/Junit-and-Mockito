package com.te.employeeManagement.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
@Document(collection = "Details")
public class EmployeeDetails {
	@Id
	private int id;
	private int empcode;
	private int empOfficialId;
	private long ctc;
	private String reportingManager;
	

}
