package com.medsell.MedVault.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medsell.MedVault.entity.Notification;
import com.medsell.MedVault.service.NotificationService;
import com.medsell.MedVault.service.UserService;

@Controller
@RequestMapping("/medvault/admin")
public class DashboardController {

	@Autowired
	UserService userService;
	@Autowired
	NotificationService notificationService;

	@GetMapping("/dashboard")
	public String dashBoard(Model model) {

		int userCount = userService.userCount();
		double calculateActiveUserPercentage = userService.calculateActiveUserPercentage();
		Integer intValue = Double.valueOf(calculateActiveUserPercentage).intValue();
		List<Notification> adminNotifications = notificationService.getAdminNotifications();
		int calculateNoReadNotification = notificationService.calculateReadStatus();

		model.addAttribute("usercountpercent", intValue);
		model.addAttribute("usercount", userCount);
		model.addAttribute("adminNotifications", adminNotifications);
		model.addAttribute("calculateNoReadNotification", calculateNoReadNotification);

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + adminNotifications);
		System.out
				.println(">>>>>>>>>>>>>>>notification calculateIsActive>>>>>>>>>>>>>>>>" + calculateNoReadNotification);
		return "admin/index";
	}

	@ResponseBody
	@GetMapping("/getNotification")
	public int getNotificatin(Model model) {

		int calculateNoReadNotification = notificationService.calculateReadStatus();

		model.addAttribute("calculateNoReadNotification", calculateNoReadNotification);
		System.out
				.println(">>>>>>>>>>>>>>>notification calculateIsActive>>>>>>>>>>>>>>>>" + calculateNoReadNotification);
		return calculateNoReadNotification;
	}

	@ResponseBody
	@GetMapping("/getNotificationList")
	public List<Notification> getNotificationList(Model model) {

		List<Notification> notificationsByreadStatus = notificationService.getNotificationsByreadStatus();

		model.addAttribute("notificationsByreadStatus", notificationsByreadStatus);
		System.out.println(
				">>>>>>>>>>>>>>>notification notificationsByreadStatus>>>>>>>>>>>>>>>>" + notificationsByreadStatus);
		return notificationsByreadStatus;
	}

	@PostMapping("/updateNotificationStatus")
	@ResponseBody
	public ResponseEntity<String> updateNotificationStatus() {
		System.out.println("trying to update notificaion ");
		notificationService.updateNotificationStatus();

		return ResponseEntity.ok("Notification status updated successfully");
	}
}
