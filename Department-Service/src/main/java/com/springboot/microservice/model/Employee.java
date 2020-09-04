package com.springboot.microservice.model;

public class Employee {
    
	private int empid;
	private String empname;
	private String emploc;
	private int edid;
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmploc() {
		return emploc;
	}
	public void setEmploc(String emploc) {
		this.emploc = emploc;
	}
	public int getEdid() {
		return edid;
	}
	public void setEdid(int edid) {
		this.edid = edid;
	}
	public Employee(int empid, String empname, String emploc, int edid) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.emploc = emploc;
		this.edid = edid;
	}
	public Employee() {
		super();
	}
	
	
}
