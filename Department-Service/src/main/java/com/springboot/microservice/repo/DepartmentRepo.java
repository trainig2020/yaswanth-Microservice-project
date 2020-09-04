package com.springboot.microservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.microservice.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
