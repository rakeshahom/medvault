package com.medsell.MedVault.service;

import java.util.List;

import com.medsell.MedVault.dto.CategoryDto;

public interface MedCatService {

	CategoryDto createMedCat(CategoryDto categoryDto);

	boolean categoryExist(String category);

	CategoryDto updateMedicine_category(CategoryDto Medicine_categoryDto, int medcatId);

	CategoryDto getMedicine_categoryById(int medcatId);

	List<CategoryDto> getAllMedicine_categorys();

	void deleteMedicine_categorys(int medcatId);
}
