package com.datta.notification_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.datta.employee_service.models.Employee;
import com.datta.notification_service.exceptions.ResourceNotFoundException;
import com.datta.notification_service.models.Notification;
import com.datta.notification_service.repositories.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	public Notification createPartialNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	@KafkaListener(topics = "employee-events", groupId = "notification-group")
	public void consumeEmployeeEvent(Employee employee) {
		Notification notification = new Notification();
		notification.setId(employee.getId());
		notification.setEmployeeId(employee.getEmployeeId());
		notification.setName(employee.getFirstName() + " " + employee.getLastName());
		notification.setGender(employee.getGender());
		notification.setAge(employee.getAge());
		notification.setDepartment(employee.getDepartment());
		notification.setDistanceFromHomeKm(employee.getDistanceFromHomeKm());
		notification.setState(employee.getState());
		notification.setEthnicity(employee.getEthnicity());
		notification.setEducationField(employee.getEducationField());
		notification.setJobRole(employee.getJobRole());
		notification.setMaritalStatus(employee.getMaritalStatus());
		notification.setSalary(employee.getSalary());
		notification.setStockOptionLevel(employee.getStockOptionLevel());
		notification.setOverTime(employee.getOverTime());
		notification.setHireDate(employee.getHireDate());
		notification.setAttrition(employee.getAttrition());
		notification.setYearsAtCompany(employee.getYearsAtCompany());
		notification.setYearsInMostRecentRole(employee.getYearsInMostRecentRole());
		notification.setYearsSinceLastPromotion(employee.getYearsSinceLastPromotion());
		notification.setYearsWithCurrManager(employee.getYearsWithCurrManager());
		createPartialNotification(notification);
	}

	public List<Notification> getAllNotifications(Long createdBy_hrId) {
		return notificationRepository.findByCreatedByHrId(createdBy_hrId);
	}

	public Notification getNotificationById(String id) {
		return notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id " + id));
	}
	
	public Notification createNotification(String id, String comment, Long createdBy) {
		Notification notification = getNotificationById(id);
		notification.setComment(comment);
		notification.setCreatedBy_hrId(createdBy);
		return notificationRepository.save(notification);
	}

	
	public Notification updateNotification(String id, String comment) {
		Notification notification = getNotificationById(id);
		notification.setComment(comment);
		return notificationRepository.save(notification);
	}
	
	public void removeNotification(String id) {
		Notification notification = getNotificationById(id);
		notificationRepository.delete(notification);
	}

}
