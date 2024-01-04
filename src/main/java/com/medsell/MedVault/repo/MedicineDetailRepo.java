package com.medsell.MedVault.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medsell.MedVault.entity.MedicineDetails;

public interface MedicineDetailRepo extends JpaRepository<MedicineDetails, Integer> {
	
	Optional<MedicineDetails> findByName(String name);
}
