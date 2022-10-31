package com.te.employeeManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.te.employeeManagement.EmpRepositary.EmpRepo;
import com.te.employeeManagement.EmpServiceImp.EmpServiceImp;
import com.te.employeeManagement.EmployeeDto.Employeedto;
import com.te.employeeManagement.Wrapper.Wrapper;
import com.te.employeeManagement.entity.Address;
import com.te.employeeManagement.entity.Bank;
import com.te.employeeManagement.entity.Dept;
import com.te.employeeManagement.entity.Employee;
import com.te.employeeManagement.entity.EmployeeDetails;



@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmpServiceImp empServiceImp;

	@Mock
	EmpRepo empRepo;

//
//	@Test
//	public void add() {
//		Employee build = Employee.builder().age(25).lastName("utekar").firstName("komal").build();
//		Employeedto build2 = Employeedto.builder().age(20).lastName("utekar").firstName("trupti").build();
//		when(empRepo.save(build)).thenReturn(build);
//		Employee add = empServiceImp.save(build2);
//		assertEquals(build, add);
//	}
//

	@Test
	public void find() {
		Optional<Employee> build = Optional
				.of(Employee.builder().empId(1).age(25).lastName("utekar").firstName("komal").build());
		when(empRepo.findById(Mockito.any())).thenReturn(build);
		
		Employee find = empServiceImp.findById(1);
		assertEquals(build.get().getAge(), find.getAge());
	}



	@Test
	void delete() {
		Employee build = Employee.builder().age(25).empId(1).lastName("utekar").firstName("komal").build();
		when(empRepo.findById(build.getEmpId())).thenReturn(Optional.of(build));
		empServiceImp.delete(1);
		verify(empRepo).deleteById(build.getEmpId());
	}

	

	@Test
	void findAll() {
		List<Employee> asList = Arrays.asList(Employee.builder().age(25).empId(1).lastName("utekar").firstName("aarti").build(),
				Employee.builder().age(20).empId(2).lastName("utekar").firstName("snehal").build());
		when(empRepo.findAll()).thenReturn(asList);
		List<Employee> findall = empServiceImp.FindAll();
		assertThat(asList.equals(findall));
	}

//	@Test
//	void update() {
//		Employee build = Employee.builder().age(25).empId(2).lastName("utekar").firstName("komal").build();
//		Employeedto build1 = Employeedto.builder().age(25).lastName("utekar").firstName("trupti").build();
//		when(empRepo.findById(2)).thenReturn(Optional.of(build));
//		when(empRepo.save(build)).thenReturn((build));
//		Employee update = empServiceImp.update(2, build1);
//		assertEquals(build.getAge(), update.getAge());
//	}
	
//	@Test
//	void updateCatch() {
//		Employee build = Employee.builder().age(25).empId(2).lastName("utekar").firstName("komal").build();
//		Employeedto build1 = Employeedto.builder().age(25).lastName("amit").firstName("amit").build();
//		RuntimeException assertThrows2 = assertThrows(RuntimeException.class,() -> empServiceImp.update(1021, build1));
//		assertThat(assertThrows2.getMessage().equals("Id is not present"));
	//}
	@Test
	void testAddEmployeeDetailsfailure() throws Exception {
		Wrapper wrapper = Wrapper.builder().employee(Employee.builder().firstName("komal").lastName("utekar").build())
				.employeeDetails(
						EmployeeDetails.builder().id(45).ctc(47000).reportingManager("Rakesh").build())
				.build();
		RuntimeException exception = assertThrows(RuntimeException.class, () -> empServiceImp.Add(wrapper));
		assertEquals("Employee Details Not Inserted", exception.getMessage());

	}

	
	
}
	
	

	

