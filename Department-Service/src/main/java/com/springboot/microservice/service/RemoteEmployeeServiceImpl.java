package com.springboot.microservice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.springboot.microservice.model.Employee;
import com.springboot.microservice.model.ListOfEmployees;

@Service
public class RemoteEmployeeServiceImpl implements RemoteEmployeeService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public  ListOfEmployees getAllEmployees(int edid) {
		ListOfEmployees lstemp = restTemplate.getForObject("http://Employee-Service:8282/employees/GetAll/"+edid, ListOfEmployees.class);
		return lstemp;
	}

	@Override
	public Employee getEmployee(int empid) {
		Employee employee = restTemplate.getForObject("http://Employee-Service:8282/employees/"+empid, Employee.class);
		return employee;
	}

	@Override
	public Employee addEmployee(Employee employee,int edid) {
	    return restTemplate.postForObject("http://Employee-Service:8282/employees/"+edid+"/addEmployee", employee, Employee.class);
		
	}

	@Override
	public void updateEmployee(Employee employee,int edid,int empid) {
		//return restTemplate.postForObject("http://Employee-Service:8282/employees/"+edid+"/updateEmployee/"+empid, employee, Employee.class);
		restTemplate.put("http://Employee-Service:8282/employees/"+edid+"/updateEmployee/"+empid, employee);
	}

	@Override
	public void deleteEmployee(int edid) {
		restTemplate.delete("http://Employee-Service:8282/employees/deleteEmployee/"+edid, edid);
		
	}

	@Override
	public void deleteSingleEmployee(int edid, int empid) {
		restTemplate.delete("http://Employee-Service:8282/employees/deleteAll/"+edid+"/"+empid);
		
	}

}
