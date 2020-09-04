package com.springboot.microservice.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.microservice.model.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Modifying
    @Query(value="delete from employee e where e.edid=?1 and e.empid=?2",nativeQuery = true)
	public void deleteByEdidAndEmpid(int edid,int empid);
    
	@Modifying
	@Query(value = "delete from employee where edid=?1",nativeQuery =true)
	public void deleteByEdId(int edid);
	
	@Modifying
	@Query(value = "select * from employee e where e.edid=?1",nativeQuery = true)
	public List<Employee> findAllByEdId(int edid);
}
