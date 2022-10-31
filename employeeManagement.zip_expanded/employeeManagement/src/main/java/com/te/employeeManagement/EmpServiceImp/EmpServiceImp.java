package com.te.employeeManagement.EmpServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.te.employeeManagement.EmpRepositary.EmpDetailsRepo;
import com.te.employeeManagement.EmpRepositary.EmpRepo;
import com.te.employeeManagement.EmpService.EmpService;
import com.te.employeeManagement.EmployeeDto.Employeedto;
import com.te.employeeManagement.Exception.EmployeeNotFoundException;
import com.te.employeeManagement.Wrapper.Wrapper;
import com.te.employeeManagement.entity.Employee;
import com.te.employeeManagement.entity.EmployeeDetails;

import ch.qos.logback.classic.Logger;

@Service
public class EmpServiceImp implements EmpService {

	@Autowired
	EmpRepo emprepo;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	EmpDetailsRepo details;

	

	@Override
	public Employee save(Employee employee) {
		try {
			Employee save = emprepo.save(employee);

			if (save == null) {
				throw new EmployeeNotFoundException("Employee Not Save");
			} else {
				SimpleMailMessage Message = new SimpleMailMessage();
				Message.setFrom("komalutekar8080@gmail.com");
				Message.setTo(save.getEmail());
				Message.setText(save.getFirstName() + " " + save.getLastName() + " " + "your registration is successfull");
				Message.setSubject("welcome to testyantra");
				javaMailSender.send(Message);
				System.out.println("Mail send successfully");
				return save;
			}
		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Something Went Wrong");
		}

	}

	@Override
	public Employee findById(int empId) {
		try {
			Optional<Employee> findById = emprepo.findById(empId);

			if (!findById.isPresent()) {
				throw new EmployeeNotFoundException("EmpId is not present");
			} else {
				return findById.get();

			}
		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("EmpId does not exist");
		}
	}

	@Override
	public void delete(int empId) {
		try {
			Optional<Employee> findById = emprepo.findById(empId);
			if (!findById.isPresent()) {
				throw new EmployeeNotFoundException("EmpId is NOt Found");
			} else {
				emprepo.deleteById(empId);
			}

		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee data is not deleted");
		}
	}

	@Override
	public List<Employee> FindAll() {

		List<Employee> findAll = emprepo.findAll();

		return findAll;
	}

	@Override
	public Employee update(Employee employee) {
		Optional<Employee> findById = emprepo.findById(employee.getEmpId());
		try {
			if (findById.isPresent()) {
				Employee employee2 = findById.get();
				BeanUtils.copyProperties(employee, employee2);
				return emprepo.save(employee2);
			} else {
				throw new EmployeeNotFoundException("EmpId is not present");
			}
		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Data Is Not Present");

		}

	}

	@Override
	public Wrapper Add(Wrapper wrapper) {
		Employee employee = wrapper.getEmployee();
		Employee save = emprepo.save(employee);
		EmployeeDetails employeeDetails = wrapper.getEmployeeDetails();
		EmployeeDetails save2 = details.save(employeeDetails);
		Wrapper employeeInfo = new Wrapper();
		employeeInfo.setEmployee(employee);
		employeeInfo.setEmployeeDetails(employeeDetails);
		return employeeInfo;

	}

}