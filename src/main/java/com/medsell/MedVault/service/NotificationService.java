package com.medsell.MedVault.service;

import java.util.List;

import com.medsell.MedVault.entity.Notification;

public interface NotificationService {

	void createNotificationForAdmin(String message, int user_id);
	List<Notification> getAdminNotifications();
	int notificationCount();
	int calculateReadStatus();
	 List<Notification> getNotificationsByreadStatus();
	 void updateNotificationStatus();
}
