package com.springboot.microservice.model;

import java.util.List;

public class ListOfEmployees {
    private List<Employee> listOfEmployee;

	public List<Employee> getListOfEmployee() {
		return listOfEmployee;
	}

	public void setListOfEmployee(List<Employee> listOfEmployee) {
		this.listOfEmployee = listOfEmployee;
	}

	public ListOfEmployees(List<Employee> listOfEmployee) {
		super();
		this.listOfEmployee = listOfEmployee;
	}

	public ListOfEmployees() {
		super();
	}
    
    
    
    
}
