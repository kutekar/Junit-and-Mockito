package com.te.employeeManagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	@Id
	
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private double contactNo;
	private int age;
	private double salary;
	
//	private EmployeeDetails employeedetails;
	
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "empId")
	private List<Address>address;
	 
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name="empId")
	private List<Dept> dept;
	
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name="empId")
	private List<Bank> bank;

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNo=" + contactNo + ", age=" + age + ", salary=" + salary + ", address=" + address
				+ ", dept=" + dept + ", bank=" + bank + "]";
	}
	
	
	

}
