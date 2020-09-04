package com.springboot.microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.model.Department;
import com.springboot.microservice.repo.DepartmentRepo;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo departmentRepo;
	
	public List<Department> getAllDepartments(){
		System.out.println("SErvice");
		return departmentRepo.findAll();	
	}
	
	public Optional<Department> getDepartment(int deptid) {
		return departmentRepo.findById(deptid);
	}
	
	public Department addDepartment(Department department) {
		System.out.println("Department value: " + department.getDepthead());
		return departmentRepo.save(department);
	}
	
	public Department updateDepartment(Department department) {
		return departmentRepo.save(department);
	}
	
	public void deleteDepartment(int deptid) {
		departmentRepo.deleteById(deptid);
	}
}
