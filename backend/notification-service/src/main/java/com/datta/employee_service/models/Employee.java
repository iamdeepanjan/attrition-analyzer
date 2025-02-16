package com.datta.employee_service.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	
	private String id;
	@JsonProperty("EmployeeID")
    private String employeeId;
	@JsonProperty("FirstName")
    private String firstName;
	@JsonProperty("LastName")
    private String lastName;
	@JsonProperty("Gender")
    private String gender;
	@JsonProperty("Age")
    private int age;
	@JsonProperty("BusinessTravel")
    private String businessTravel;
	@JsonProperty("Department")
    private String department;
    @JsonProperty("DistanceFromHome_km")
    private int distanceFromHomeKm;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Ethnicity")
    private String ethnicity;
    @JsonProperty("Education")
    private int education;
    @JsonProperty("EducationField")
    private String educationField;
    @JsonProperty("JobRole")
    private String jobRole;
    @JsonProperty("MaritalStatus")
    private String maritalStatus;
    @JsonProperty("Salary")
    private long salary;
    @JsonProperty("StockOptionLevel")
    private int stockOptionLevel;
    @JsonProperty("OverTime")
    private String overTime;
    @JsonProperty("HireDate")
    private LocalDate hireDate;
    @JsonProperty("Attrition")
    private String attrition;
    @JsonProperty("YearsAtCompany")
    private int yearsAtCompany;
    @JsonProperty("YearsInMostRecentRole")
    private int yearsInMostRecentRole;
    @JsonProperty("YearsSinceLastPromotion")
    private int yearsSinceLastPromotion;
    @JsonProperty("YearsWithCurrManager")
    private int yearsWithCurrManager;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getBusinessTravel() {
        return businessTravel;
    }

    public void setBusinessTravel(String businessTravel) {
        this.businessTravel = businessTravel;
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

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
