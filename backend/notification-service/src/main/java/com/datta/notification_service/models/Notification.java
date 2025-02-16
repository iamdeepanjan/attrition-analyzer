package com.datta.notification_service.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification {

	@Id
	private String id;
	private String employeeId;
	private String name;
	private String gender;
	private int age;
	private String department;
	private int distanceFromHomeKm;
	private String state;
	private String ethnicity;
	private String educationField;
	private String jobRole;
	private String maritalStatus;
	private long salary;
	private int stockOptionLevel;
	private String overTime;
	private LocalDate hireDate;
	private String attrition;
	private int yearsAtCompany;
	private int yearsInMostRecentRole;
	private int yearsSinceLastPromotion;
	private int yearsWithCurrManager;
	private String comment;
	private Long createdByHrId;
	
	public Notification() {}

	public Notification(String id, String employeeId, String name, String gender, int age, String department,
			int distanceFromHomeKm, String state, String ethnicity, String educationField, String jobRole,
			String maritalStatus, long salary, int stockOptionLevel, String overTime, LocalDate hireDate,
			String attrition, int yearsAtCompany, int yearsInMostRecentRole, int yearsSinceLastPromotion,
			int yearsWithCurrManager, String comment, Long createdBy_hrId) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.department = department;
		this.distanceFromHomeKm = distanceFromHomeKm;
		this.state = state;
		this.ethnicity = ethnicity;
		this.educationField = educationField;
		this.jobRole = jobRole;
		this.maritalStatus = maritalStatus;
		this.salary = salary;
		this.stockOptionLevel = stockOptionLevel;
		this.overTime = overTime;
		this.hireDate = hireDate;
		this.attrition = attrition;
		this.yearsAtCompany = yearsAtCompany;
		this.yearsInMostRecentRole = yearsInMostRecentRole;
		this.yearsSinceLastPromotion = yearsSinceLastPromotion;
		this.yearsWithCurrManager = yearsWithCurrManager;
		this.comment = comment;
		this.createdByHrId = createdBy_hrId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getDistanceFromHomeKm() {
		return distanceFromHomeKm;
	}

	public void setDistanceFromHomeKm(int distanceFromHomeKm) {
		this.distanceFromHomeKm = distanceFromHomeKm;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getEducationField() {
		return educationField;
	}

	public void setEducationField(String educationField) {
		this.educationField = educationField;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public int getStockOptionLevel() {
		return stockOptionLevel;
	}

	public void setStockOptionLevel(int stockOptionLevel) {
		this.stockOptionLevel = stockOptionLevel;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getAttrition() {
		return attrition;
	}

	public void setAttrition(String attrition) {
		this.attrition = attrition;
	}

	public int getYearsAtCompany() {
		return yearsAtCompany;
	}

	public void setYearsAtCompany(int yearsAtCompany) {
		this.yearsAtCompany = yearsAtCompany;
	}

	public int getYearsInMostRecentRole() {
		return yearsInMostRecentRole;
	}

	public void setYearsInMostRecentRole(int yearsInMostRecentRole) {
		this.yearsInMostRecentRole = yearsInMostRecentRole;
	}

	public int getYearsSinceLastPromotion() {
		return yearsSinceLastPromotion;
	}

	public void setYearsSinceLastPromotion(int yearsSinceLastPromotion) {
		this.yearsSinceLastPromotion = yearsSinceLastPromotion;
	}

	public int getYearsWithCurrManager() {
		return yearsWithCurrManager;
	}

	public void setYearsWithCurrManager(int yearsWithCurrManager) {
		this.yearsWithCurrManager = yearsWithCurrManager;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCreatedByHrId() {
		return createdByHrId;
	}

	public void setCreatedBy_hrId(Long createdBy_hrId) {
		this.createdByHrId = createdBy_hrId;
	}
	
}
