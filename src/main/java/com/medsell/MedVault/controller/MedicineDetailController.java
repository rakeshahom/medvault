package com.medsell.MedVault.controller;

import java.util.Date;
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
import com.medsell.MedVault.dto.MedicineDetailsDto;
import com.medsell.MedVault.entity.MedicineDetails;
import com.medsell.MedVault.service.MedCatService;
import com.medsell.MedVault.service.MedicineDetailService;

@Controller
@RequestMapping("/medvault")
public class MedicineDetailController {
	@Autowired
	MedCatService medCatService;

	@Autowired
	private MedicineDetailService mService;

	@GetMapping("/medicine/createmedicine")
	public String getCreateMedicineDetails(Model model) {
		List<CategoryDto> allMedicine_categorys = medCatService.getAllMedicine_categorys();
		model.addAttribute("categoryDto", allMedicine_categorys);
		model.addAttribute("medicineDetailDto", new MedicineDetails());
		return "admin/create_medicine_details";
	}

	@PostMapping("/medicine/createmedicine")
	public String createMedicineDetails(Model model,
			@Valid @ModelAttribute("medicineDetailDto") MedicineDetailsDto medicineDetailDto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		System.err.println("call post method......................!");
		try {
			if (mService.medicineDetailExist(medicineDetailDto.getName())) {
				result.addError(
						new FieldError("MedicineDetails", "medicinename", "This medicine name already exists."));
			}

			if (result.hasErrors()) {
				model.addAttribute("model", medicineDetailDto);
				return "admin/create_medicine_details"; // Show errors on the signup form
			}
			//medicineDetailDto.setCategory(medicineDetailDto.getCategory());
			medicineDetailDto.setIsActive(0);
			medicineDetailDto.setCreated_date(new Date());
			mService.createMedicineDetail(medicineDetailDto);

			redirectAttributes.addFlashAttribute("flashMessage", "Your information saved successfully!");
			return "redirect:/medvault/medicine/createmedicine";

		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("model", medicineDetailDto);
			model.addAttribute("failureMessage", "An error occurred while signing up.");
			return "admin/create_medicine_details";
		}
	}
//	@ResponseBody
//	@GetMapping("/medicine/getallmedicines")
//	public List<MedicineDetailsDto> getAllMedicines(Model model) {
//		List<CategoryDto> allMedicine_categorys = medCatService.getAllMedicine_categorys();
//		List<MedicineDetailsDto> allmedicine = mService.getAllMedicineDetails();
//		//model.addAttribute("allmedicine", allmedicine);
//		//model.addAttribute("allcate", allMedicine_categorys);
//		return allmedicine;
//	}

	@GetMapping("/medicine/getallmedicine")
	public String getAllMedicine(Model model) {
		List<CategoryDto> allMedicine_categorys = medCatService.getAllMedicine_categorys();
		List<MedicineDetailsDto> allmedicine = mService.getAllMedicineDetails();
		model.addAttribute("allmedicine", allmedicine);
		model.addAttribute("allcate", allMedicine_categorys);
		return "admin/managemedicine";
	}

	@GetMapping("/medicine/{id}/edit")
	public String editMedicine(@PathVariable int id, Model model) {

		MedicineDetailsDto medicineDetailById = mService.getMedicineDetailById(id);
		model.addAttribute("medicineDetailById", medicineDetailById);

		return "editRoleForm"; //
	}

	@GetMapping("/medicine/{id}/delete")
	public String deleteMedicine(@PathVariable int id, RedirectAttributes redirectAttributes) {

		mService.deleteMedicineDetails(id);
		redirectAttributes.addFlashAttribute("deleteSuccess", "MedicineDetails deleted successfully!");
		return "redirect:/medvault/medicine/getallmedicine";
	}
}
