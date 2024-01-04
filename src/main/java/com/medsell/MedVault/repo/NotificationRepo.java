package com.medsell.MedVault.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.medsell.MedVault.entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

	int countByReadStatus(int ReadStatus);

	List<Notification> findByreadStatus(int readStatus);
	
	
	@Transactional
	@Modifying
    @Query("update Notification n set n.readStatus = :newStatus where n.readStatus = :oldStatus")
    void updateByReadStatus(int oldStatus, int newStatus);
}
