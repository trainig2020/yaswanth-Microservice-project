package com.springboot.microservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentFallbackController {
    
	@RequestMapping("/departmentfallback")
	public String departmentFallback() {
		return "The Service is Down or Taking too much time to respond.Please try again later";
	}
	 
}
