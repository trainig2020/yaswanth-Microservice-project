package com.springboot.microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.model.Employee;
import com.springboot.microservice.model.ListOfEmployees;
import com.springboot.microservice.repo.EmployeeRepository;

@Service
public class EmployeeService {
    
	@Autowired
	private EmployeeRepository  employeeRepository;
	
	public ListOfEmployees getAllEmployess(int edid){
		List<Employee> emplist = employeeRepository.findAllByEdId(edid);
		ListOfEmployees lst=new ListOfEmployees();
		lst.setListOfEmployee(emplist);
		return lst;
	}
	public Optional<Employee> GetEmployee(int empid) {
		return employeeRepository.findById(empid);
	}
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	public void deleteEmployee(int edid) {
		employeeRepository.deleteByEdId(edid);
	}
	public void deleteEmployeeByEdid(int edid,int empid) {
		employeeRepository.deleteByEdidAndEmpid(edid, empid);
	}
}
