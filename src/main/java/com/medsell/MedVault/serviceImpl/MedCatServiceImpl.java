/**
 * 
 */
package com.medsell.MedVault.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsell.MedVault.Exception.ResourceNotFoundException;
import com.medsell.MedVault.dto.CategoryDto;
import com.medsell.MedVault.entity.Category;
import com.medsell.MedVault.repo.Med_catRepo;
import com.medsell.MedVault.service.MedCatService;

/**
 * 
 */
@Service
public class MedCatServiceImpl implements MedCatService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Med_catRepo medcatCatRepo;

	@Override
	public CategoryDto createMedCat(CategoryDto Medicine_categoryDto) {
		Category category = this.dtoToMedicineCategory(Medicine_categoryDto);
		Category savecategory = this.medcatCatRepo.save(category);
		CategoryDto categoryToDto = this.categoryToDto(savecategory);
		return categoryToDto;
	}

	@Override
	public boolean categoryExist(String category) {

		return findByName(category).isPresent();
	}

	public Optional<Category> findByName(String category) {
		return this.medcatCatRepo.findByName(category);
	}

	@Override
	public CategoryDto updateMedicine_category(CategoryDto Medicine_categoryDto, int medcatId) {
		Category category = this.medcatCatRepo.findById(medcatId)
				.orElseThrow(() -> new ResourceNotFoundException("Medicine_category", "Id", medcatId));

		category.setName(Medicine_categoryDto.getName());

		category.setDescription(Medicine_categoryDto.getDescription());
		category.setIsActive(Medicine_categoryDto.getIsActive());

		Category updatecategory = this.medcatCatRepo.save(category);
		CategoryDto categoryDTO2 = this.categoryToDto(updatecategory);
		return categoryDTO2;
	}

	@Override
	public CategoryDto getMedicine_categoryById(int medcatId) {
		Category category = this.medcatCatRepo.findById(medcatId).get();
		CategoryDto categoryToDto = this.categoryToDto(category);
		return categoryToDto;
	}

	@Override
	public List<CategoryDto> getAllMedicine_categorys() {
		List<Category> categorys = this.medcatCatRepo.findAll();
		List<CategoryDto> collect = categorys.stream().map(category -> this.categoryToDto(category))
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public void deleteMedicine_categorys(int medcatId) {
		Category category = this.medcatCatRepo.findById(medcatId)
				.orElseThrow(() -> new ResourceNotFoundException("Medicine_categorys", "Id", medcatId));
		this.medcatCatRepo.delete(category);
	}

	private Category dtoToMedicineCategory(CategoryDto medicine_categoryDto) {
		Category map = this.modelMapper.map(medicine_categoryDto, Category.class);
		return map;
	}

	private CategoryDto categoryToDto(Category category) {
		CategoryDto map = this.modelMapper.map(category, CategoryDto.class);
		return map;

	}

}
