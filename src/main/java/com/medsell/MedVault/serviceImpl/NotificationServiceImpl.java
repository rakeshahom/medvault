package com.medsell.MedVault.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsell.MedVault.entity.Notification;
import com.medsell.MedVault.repo.NotificationRepo;
import com.medsell.MedVault.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepo notificationrepo;

	@Override
	public void createNotificationForAdmin(String message, int user_id) {
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setCreated_date(new Date());
		notification.setUser_id(user_id);
		notification.setReadStatus(0);

		notificationrepo.save(notification);
	}

	@Override
	public List<Notification> getAdminNotifications() {

		return notificationrepo.findAll();
	}

	@Override
	public List<Notification> getNotificationsByreadStatus() {

		return notificationrepo.findByreadStatus(0);
	}

	@Override
	public int notificationCount() {
		int count = (int) this.notificationrepo.count();
		return count;
	}

	@Override
	public int calculateReadStatus() {
		// long totalnotificationUsers = notificationrepo.count();
		int noti = notificationrepo.countByReadStatus(0); // Assuming 1 represents active users

		if (noti == 0) {
			return 0; // To avoid division by zero
		}

		return noti;
	}

	public void updateNotificationStatus() {

		int oldStatus = 0;
		int newStatus = 1;

		notificationrepo.updateByReadStatus(oldStatus, newStatus);
	}

}
