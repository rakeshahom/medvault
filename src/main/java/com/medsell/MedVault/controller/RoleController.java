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

import com.medsell.MedVault.dto.RoleDto;
import com.medsell.MedVault.dto.UserDto;
import com.medsell.MedVault.entity.Role;
import com.medsell.MedVault.service.RoleService;
import com.medsell.MedVault.service.UserService;

@Controller
@RequestMapping("/medvault")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/role/createrole")
	public String getCreateRole(Model model) {
	//	model.addAttribute("message", "Hello, Thymeleaf!");
//		List<UserDto> allUsers = userService.getAllUsers();
//		model.addAttribute("users", allUsers);
		model.addAttribute("roleDto", new Role());
		return "admin/create_role";
	}
	
	

	@PostMapping("/role/createrole")
	public String createRole(Model model, @Valid @ModelAttribute("roleDto") RoleDto roleDto, BindingResult result,
			RedirectAttributes redirectAttributes) {

		try {
			if (roleService.roleExist(roleDto.getName())) {
				result.addError(new FieldError("Role", "rolename", "This rolename already exists."));
			}

			if (result.hasErrors()) {
				model.addAttribute("model", roleDto);
				return "admin/create_role"; 
			}

			roleDto.setIsActive("0");
			roleDto.setCreated_date(new Date());
			roleService.createRole(roleDto);

			redirectAttributes.addFlashAttribute("flashMessage", "Your information saved successfully!");
			return "redirect:/medvault/role/createrole";
		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("model", roleDto);
			model.addAttribute("failureMessage", "An error occurred while signing up.");
			return "admin/create_role";
		}
	}

	

	@GetMapping("/role/getallrole")
	public String getAllRoles(Model model) {
		List<UserDto> allUsers = userService.getAllUsers();
		List<RoleDto> allRoles = roleService.getAllRoles();
		model.addAttribute("roles", allRoles);
		model.addAttribute("users", allUsers);
		return "admin/managerole";
	}

	@GetMapping("/role/{id}/edit")
	public String editRole(@PathVariable int id, Model model) {
		
	 RoleDto roleById = roleService.getRoleById(id);
		model.addAttribute("role", roleById);
		 
		return "editRoleForm"; // 
	}

	@GetMapping("/role/{id}/delete")
	public String deleteRole(@PathVariable int id, RedirectAttributes redirectAttributes) {
		
		roleService.deleteRoles(id);
		 redirectAttributes.addFlashAttribute("deleteSuccess", "Role deleted successfully!");
		return "redirect:/medvault/role/getallrole"; 
	}
}
