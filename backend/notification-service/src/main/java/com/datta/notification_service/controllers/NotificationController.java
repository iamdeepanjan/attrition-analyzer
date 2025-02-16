package com.datta.notification_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datta.notification_service.models.Notification;
import com.datta.notification_service.models.NotificationRequestDTO;
import com.datta.notification_service.services.NotificationService;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/allNotification/{createdBy_hrId}")
	public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable Long createdBy_hrId) {
		return new ResponseEntity<List<Notification>>(notificationService.getAllNotifications(createdBy_hrId), HttpStatus.OK);
	}

	@GetMapping("/aNotification/{id}")
	public ResponseEntity<Notification> getANotification(@PathVariable String id) {
		return new ResponseEntity<Notification>(notificationService.getNotificationById(id), HttpStatus.OK);
	}

	@PostMapping("/aNotification/{id}")
	public ResponseEntity<Notification> createNotification(@PathVariable String id,
			@RequestBody NotificationRequestDTO updateRequest) {
		Notification updateNotification = notificationService.createNotification(id, updateRequest.getComment(),
				updateRequest.getCreatedByHrId());
		return new ResponseEntity<Notification>(updateNotification, HttpStatus.CREATED);
	}

	@PutMapping("/aNotification/{id}")
	public ResponseEntity<Notification> updateNotification(@PathVariable String id, @RequestBody NotificationRequestDTO updateRequest) {
		return new ResponseEntity<Notification>(notificationService.updateNotification(id, updateRequest.getComment()),
				HttpStatus.OK);
	}

	@DeleteMapping("/aNotification/{id}")
	public ResponseEntity<Void> removeNotification(@PathVariable String id) {
		notificationService.removeNotification(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
