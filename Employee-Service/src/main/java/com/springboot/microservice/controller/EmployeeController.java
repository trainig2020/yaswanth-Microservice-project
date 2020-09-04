package com.springboot.microservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.model.Employee;
import com.springboot.microservice.model.ListOfEmployees;
import com.springboot.microservice.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
	@Autowired
	private EmployeeService employeeService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping("/GetAll/{edid}")
	public ListOfEmployees getAllEmployees(@PathVariable int edid){
		logger.info("Getting All Employees");
		return employeeService.getAllEmployess(edid);
	}
	
	@GetMapping("/{empid}")
	public Optional<Employee> getEmployee(@PathVariable int empid) {
		logger.info("Getting All Employees");
		return employeeService.GetEmployee(empid);
	}
	
	@PostMapping("/{edid}/addEmployee")
	public void addEmployee(@PathVariable int edid,@RequestBody Employee employee) {
		logger.info("Adding an employee to the database");
		employee.setEdid(edid);
		employeeService.addEmployee(employee);
	}
	
	@PutMapping("/{edid}/updateEmployee/{empid}")
	public void updateEmployee(@PathVariable int edid, @RequestBody Employee employee,@PathVariable int empid) {
		logger.info("upadating an employee");
		employee.setEdid(edid);
		employee.setEmpid(empid);
		employeeService.updateEmployee(employee);
	}
	@DeleteMapping("/deleteEmployee/{edid}")
	public void deleteEmployee(@PathVariable int edid) {
		logger.info("Deleting all employees based on deptid");
		employeeService.deleteEmployee(edid);
	}
	@DeleteMapping("/deleteAll/{edid}/{empid}")
	public void deleteEmployeeByEdidAndEmpid(@PathVariable int edid,@PathVariable int empid) {
		logger.info("Deletind An Employee Based On empid and deptid");
		employeeService.deleteEmployeeByEdid(edid, empid);
	}
}
