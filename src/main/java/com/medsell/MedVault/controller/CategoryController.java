package com.medsell.MedVault.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medsell.MedVault.dto.CategoryDto;
import com.medsell.MedVault.entity.Category;
import com.medsell.MedVault.service.MedCatService;

@Controller
@RequestMapping("/medvault")
public class CategoryController {

	@Autowired
	private MedCatService categoryService;

	
	
	@GetMapping("/category/createcategory")
	public String getCreateMedicine_category(Model model) {

		model.addAttribute("categoryDto", new Category());
		return "admin/create_category";
	}

	@PostMapping("/category/createcategory")
	public String createMedicine_category(Model model, @Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult result,
			RedirectAttributes redirectAttributes) {

		try {
			if (categoryService.categoryExist(categoryDto.getName())) {
				result.addError(new FieldError("Medicine_category", "categoryname", "This categoryname already exists."));
			}

			if (result.hasErrors()) {
				model.addAttribute("model", categoryDto);
				return "admin/create_category"; // Show errors on the signup form
			}

			categoryDto.setIsActive(0);
		
			categoryService.createMedCat(categoryDto);

			redirectAttributes.addFlashAttribute("flashMessage", "Your information saved successfully!");
			return "redirect:/medvault/category/createcategory";
		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("model", categoryDto);
			model.addAttribute("failureMessage", "An error occurred while signing up.");
			return "admin/create_category";
		}
	}

	

	@GetMapping("/category/getallcategory")
	public String getAllMedicine_categorys(Model model) {
		
		List<CategoryDto> allMedicine_categorys = categoryService.getAllMedicine_categorys();
		model.addAttribute("categorys", allMedicine_categorys);

		return "admin/managecategory";
	}

	@GetMapping("/category/{id}/edit")
	public String editMedicine_category(@PathVariable int id, Model model) {
		
	 CategoryDto categoryById = categoryService.getMedicine_categoryById(id);
		model.addAttribute("category", categoryById);
		 
		return "editMedicine_categoryForm"; // 
	}

	@GetMapping("/category/{id}/delete")
	public String deleteMedicine_category(@PathVariable int id, RedirectAttributes redirectAttributes) {
		
		categoryService.deleteMedicine_categorys(id);
		 redirectAttributes.addFlashAttribute("deleteSuccess", "Medicine_category deleted successfully!");
		return "redirect:/medvault/category/getallcategory"; 
	}
}
