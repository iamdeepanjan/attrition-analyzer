package com.datta.employee_service.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.datta.employee_service.exceptions.ResourceNotFoundException;
import com.datta.employee_service.models.Employee;

@Service
public class EmployeeService {
	
	@Value("${external.api.url}")
    private String externalApiUrl;
	
	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "employee-events";
	
	public List<Employee> getAllEmployees(){
		 RestTemplate restTemplate = new RestTemplate();
		 try {
			 Employee[] employees = restTemplate.getForObject(externalApiUrl, Employee[].class);
			 return Arrays.asList(employees);
		} catch (RestClientException e) {
			return Collections.emptyList();
		}
	}
	
	public List<Employee> getAttritionEmployees(){
		 RestTemplate restTemplate = new RestTemplate();
		 try {
			 Employee[] employees = restTemplate.getForObject(externalApiUrl+"?Attrition=Yes", Employee[].class);
			 return Arrays.asList(employees);
		} catch (RestClientException e) {
			return Collections.emptyList();
		}
	}
	
	public Employee getEmployeeById(String id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Employee employee = restTemplate.getForObject(externalApiUrl+"/"+id, Employee.class);
			kafkaTemplate.send(TOPIC, employee);
			return employee;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Employee is not exist for this id "+ id);
		}
	}
}
