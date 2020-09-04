package com.springboot.microservice.model;

public class Department {
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
