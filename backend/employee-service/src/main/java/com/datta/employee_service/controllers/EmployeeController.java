package com.datta.employee_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datta.employee_service.models.Employee;
import com.datta.employee_service.services.EmployeeService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/allEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/allAttritionEmployees")
	public ResponseEntity<List<Employee>> getAllAttritionEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getAttritionEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/allEmployees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}
	
}
