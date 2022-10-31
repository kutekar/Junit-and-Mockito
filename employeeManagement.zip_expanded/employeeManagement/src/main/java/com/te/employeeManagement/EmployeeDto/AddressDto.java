package com.te.employeeManagement.EmployeeDto;

import lombok.Data;

@Data
public class AddressDto {
	private int addId;
	private int doorNo;
	private String city;
	private String street;
	private String buildingName;
	private String state;
	private int pincode;
	

}
