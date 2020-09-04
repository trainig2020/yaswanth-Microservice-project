package com.springboot.microservice.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.microservice.model.Employee;
import com.springboot.microservice.model.ListOfEmployees;

@Repository
public interface RemoteEmployeeService {
    
	public ListOfEmployees getAllEmployees(int edid);
	
	public Employee getEmployee(int empid);
	
	public Employee addEmployee(Employee employee,int edid);
	
	public void updateEmployee(Employee employee,int edid,int empid);
	
	public void deleteEmployee(int empid);
	
	public void deleteSingleEmployee(int edid,int empid);
 }
