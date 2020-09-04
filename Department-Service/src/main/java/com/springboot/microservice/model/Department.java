package com.springboot.microservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptid;
	private String deptname;
	private String depthead;

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDepthead() {
		return depthead;
	}

	public void setDepthead(String depthead) {
		this.depthead = depthead;
	}

	public Department(int deptid, String deptname, String depthead) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
		this.depthead = depthead;
	}

	public Department() {
		super();
	}

}
