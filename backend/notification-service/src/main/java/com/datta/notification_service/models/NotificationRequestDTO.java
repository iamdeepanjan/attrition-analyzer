package com.datta.notification_service.models;

public class NotificationRequestDTO {

	private String comment;
	private Long createdByHrId;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCreatedByHrId() {
		return createdByHrId;
	}

	public void setCreatedByHrId(Long createdBy_hrId) {
		this.createdByHrId = createdBy_hrId;
	}

}
