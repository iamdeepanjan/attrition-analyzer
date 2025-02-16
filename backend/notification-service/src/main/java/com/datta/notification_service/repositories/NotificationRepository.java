package com.datta.notification_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.datta.notification_service.models.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
	List<Notification> findByCreatedByHrId(Long createdByHrId);
}
