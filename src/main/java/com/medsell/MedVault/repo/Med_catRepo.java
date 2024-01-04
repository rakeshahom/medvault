package com.medsell.MedVault.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medsell.MedVault.entity.Category;

public interface Med_catRepo extends JpaRepository<Category ,Integer> {

	Optional<Category> findByName(String role);
}
