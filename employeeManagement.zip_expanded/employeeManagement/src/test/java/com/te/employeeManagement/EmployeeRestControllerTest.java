package com.te.employeeManagement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.employeeManagement.EmpRepositary.EmpDetailsRepo;
import com.te.employeeManagement.EmpRepositary.EmpRepo;
import com.te.employeeManagement.EmpService.EmpService;
import com.te.employeeManagement.EmpServiceImp.EmpServiceImp;
import com.te.employeeManagement.Response.Response;
import com.te.employeeManagement.Wrapper.Wrapper;
import com.te.employeeManagement.controller.MainController;
import com.te.employeeManagement.entity.Address;
import com.te.employeeManagement.entity.Bank;
import com.te.employeeManagement.entity.Dept;
import com.te.employeeManagement.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {

	private MockMvc mockmvc;

	@InjectMocks
	MainController controller;

	@Mock
	EmpService empService;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setup() {
		mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testDeleteemployeeDetails() throws Exception {
		mockmvc.perform(delete("/delete/{id}", 4).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString();
	}

	@Test
	public void testAddedEmployee() throws Exception {
		Wrapper wrapper = Wrapper.builder().employee(Employee.builder().firstName("komal").build()).build();
		String AsString = objectMapper.writeValueAsString(wrapper);

		when(empService.Add(Mockito.any())).thenReturn(wrapper);
		String contentAsString = mockmvc
				.perform(post("/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString();
		Response response = objectMapper.readValue(contentAsString, Response.class);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) response.getData();
		@SuppressWarnings("unchecked")
		Map<String, String> map1 = (Map<String, String>) map.get("employee");
		assertEquals(wrapper.getEmployee().getFirstName(), map1.get("firstName"));
	}



	@Test
	public void TestGetAllDetails() throws Exception {
		List<Employee> list = Arrays.asList(Employee.builder().firstName("komal").lastName("utekar").build(),
				Employee.builder().firstName("trupti").lastName("utekar").build());
		String AsString = objectMapper.writeValueAsString(list);
		when(empService.FindAll()).thenReturn(list);

		String contentAsString = mockmvc
				.perform(get("/findAll").contentType(MediaType.APPLICATION_JSON_VALUE).content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString(); 

		Response response = objectMapper.readValue(contentAsString, Response.class);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) response.getData();
		Map<String, Object> map = list1.get(1);
		assertEquals(list.get(1).getFirstName(), map.get("firstName"));
	}
 
	@Test
	public void testUpdateEmployee() throws Exception {
		Employee employee = Employee.builder().firstName("komal").email("komal@gmail.com").build();
		String AsString = objectMapper.writeValueAsString(employee);

		when(empService.update(Mockito.any())).thenReturn(employee);
		String contentAsString = mockmvc
				.perform(put("/update").contentType(MediaType.APPLICATION_JSON_VALUE).content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString();
		Response response = objectMapper.readValue(contentAsString, Response.class);
		@SuppressWarnings("unchecked")
		Map<String, String> map1 = (Map<String, String>) response.getData();
		System.out.println(map1);
		assertEquals(employee.getFirstName(), map1.get("firstName"));
		assertEquals(employee.getEmail(),map1.get("email"));
	}
	@Test
	public void testGetEmployee() throws Exception {
		Employee employee=Employee.builder().email("komal@gmail.com").age(16).build();
		String AsString = objectMapper.writeValueAsString(employee);

		when(empService.findById(4)).thenReturn(employee);
		String contentAsString = mockmvc
				.perform(get("/getbyid/{id}",4).contentType(MediaType.APPLICATION_JSON_VALUE).content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() 
				.getContentAsString(); 

		Response response = objectMapper.readValue(contentAsString, Response.class);
		@SuppressWarnings("unchecked")
		Map<String, String> map1 = (Map<String, String>) response.getData();
		assertEquals(employee.getAge(), map1.get("age"));
	}
}
