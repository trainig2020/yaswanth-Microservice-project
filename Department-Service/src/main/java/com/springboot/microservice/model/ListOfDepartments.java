package com.springboot.microservice.model;

import java.util.List;

public class ListOfDepartments {
    private List<Department> deptList;

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public ListOfDepartments(List<Department> deptList) {
		super();
		this.deptList = deptList;
	}

	public ListOfDepartments() {
		super();
	}
    
    
}
