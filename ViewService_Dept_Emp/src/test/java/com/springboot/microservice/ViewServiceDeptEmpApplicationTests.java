package com.springboot.microservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.microservice.controller.DeptEmpViewController;
import com.springboot.microservice.model.Department;
import com.springboot.microservice.model.Employee;
import com.springboot.microservice.model.ListOfDepartments;
import com.springboot.microservice.model.ListOfEmployees;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = DeptEmpViewController.class)
public class ViewServiceDeptEmpApplicationTests {
    
	@Autowired
	private RestTemplate restTemplate;
	
	@MockBean
	private DeptEmpViewController deptEmpViewController;
	
	ObjectMapper obj = new ObjectMapper();
	
	@Autowired
	private MockMvc mockmvc;
	
	@Test
	public void getAllDepartments() {
		Department department1  = new Department(1, "Training", "Harry");
		Department department2 =  new Department(2, "Support", "browny");
		List<Department> lst = new ArrayList<>();
		lst.add(department1);
		lst.add(department2);
		ListOfDepartments lst1 = new ListOfDepartments();
		lst1.setDeptList(lst);
		when(deptEmpViewController.getListOfDepartments()).thenReturn(lst1);
		
		try {
			mockmvc.perform(get("/DeptList").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2, lst1.getDeptList().size());
	}
	
	
	@Test
	public void addDepartment() {
		
		Department department  = new Department(1, "Training", "Harry");
				
		try {
			when(deptEmpViewController.addDepartment(department)).thenReturn(department);
			String jasontype = obj.writeValueAsString(department);
			mockmvc.perform(post("/CreateDepartment").content(jasontype)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	@Test
	public void updateDepartment() {
		
		Department department  = new Department(1, "Training", "Harry");
				
		try {
			verify(deptEmpViewController,times(0)).upadateDept(department, department.getDeptid());
			String jasontype = obj.writeValueAsString(department);
			mockmvc.perform(post("/UpdateDepartment").content(jasontype)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void deleteDepartment() {
		Department department3  = new Department(1, "Training", "Harry");
		
		try {
			verify(deptEmpViewController,times(0)).deleteDept(department3.getDeptid());
			mockmvc.perform(delete("/DeleteDepartment").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void getAllEmployees() {
		Employee e1 = new Employee(1, "yash", "kpt", 1);
		Employee e2 = new Employee(2, "yaswanth", "kpt", 1);
		ListOfEmployees lstemp = new ListOfEmployees();
		List<Employee> lst = new ArrayList<>();
		lst.add(e1);
		lst.add(e2);
		lstemp.setListOfEmployee(lst);
		when(deptEmpViewController.getAllDepartments(1)).thenReturn(lstemp);
		
		try {
			mockmvc.perform(get("/emplist").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2, lstemp.getListOfEmployee().size());
	}
	
	@Test
	public void addEmployee() {
		Employee e1 = new Employee(1, "yash", "kpt", 1);
		
		try {
			when(deptEmpViewController.addEmployee(e1, e1.getEdid())).thenReturn(e1);
			String jasontype = obj.writeValueAsString(e1);
			mockmvc.perform(post("/saveEmployee").content(jasontype)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	@Test
	public void deleteEmployee() {
		Employee e1 = new Employee(1, "yash", "kpt", 1);
		
		try {
			verify(deptEmpViewController,times(0)).deleteEmp(e1.getEdid(), e1.getEmpid());
			mockmvc.perform(delete("/deleteEmployee").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void updateEmployee() {
		Employee e1 = new Employee(1, "yash", "kpt", 1);
		try {
			verify(deptEmpViewController,times(0)).updateEmp(e1, e1.getEdid(), e1.getEmpid());
			String jasontype = obj.writeValueAsString(e1);
			mockmvc.perform(post("/updateEmployee").content(jasontype)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
