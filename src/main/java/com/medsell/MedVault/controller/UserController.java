package com.medsell.MedVault.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import com.medsell.MedVault.entity.User;
import com.medsell.MedVault.service.RoleService;
import com.medsell.MedVault.service.UserService;

@Controller
@RequestMapping("/medvault")
public class UserController {
	@Value("${project.image}")
	private String imagePath;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/user/signup")
	public String getCreateUser(Model model) {
		model.addAttribute("message", "Hello, Thymeleaf!");
		List<RoleDto> allRoles = roleService.getAllRoles();
		model.addAttribute("roleDto", allRoles);
		model.addAttribute("userDto", new User());
		return "admin/signup";
	}

	/*
	 * @PostMapping("/user/signup") public String
	 * createUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult
	 * result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
	 * 
	 * MultipartFile user_profile_image = userDto.getUser_profile_image();
	 * System.out.println("user_profile_image++"+user_profile_image); try { if
	 * (userService.userExist(userDto.getUsername())) { result.addError(new
	 * FieldError("User", "username", "This username already exists.")); }
	 * 
	 * if (result.hasErrors()) { return "admin/signup"; // Show errors on the signup
	 * form }
	 * 
	 * MultipartFile image = userDto.getUser_profile_image(); if (image != null &&
	 * !image.isEmpty()) { String uploadDir =
	 * request.getSession().getServletContext().getRealPath("/profileimages/");
	 * String filename = image.getOriginalFilename(); String filePath = uploadDir +
	 * File.separator + filename;
	 * 
	 * // Save the image to the specified directory File dest = new File(filePath);
	 * image.transferTo(dest);
	 * 
	 * // Set the image path in the userDto
	 * userDto.setUserProfileImagePath(imagePath + filename);
	 * 
	 * }
	 * 
	 * // Continue with your user creation logic String firstName =
	 * userDto.getFirst_name(); String lastName = userDto.getLast_name(); String
	 * username = generateUsername(firstName, lastName); userDto.setIsActive(0);
	 * userDto.setCreated_date(new Date()); userDto.setUsername(username);
	 * userService.createUser(userDto);
	 * 
	 * redirectAttributes.addFlashAttribute("flashMessage",
	 * "Your information saved successfully!"); return
	 * "redirect:/medvault/user/signup"; } catch (Exception e) {
	 * 
	 * System.out.println("An error occurred while signing up." + e.getMessage());
	 * return "admin/signup"; } }
	 */

	@PostMapping("/user/signup")
public String createUser(Model
	  model, @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult
	  result, RedirectAttributes redirectAttributes) {
	  
	  try { if (userService.userExist(userDto.getUsername())) { result.addError(new
	  FieldError("User", "username", "This username already exists.")); }
	  
	  if (result.hasErrors()) {
		  model.addAttribute("model", userDto);
		  return "admin/signup"; // Show errors on the signup form }
	  }
	  String firstName = userDto.getFirst_name(); String lastName =
	  userDto.getLast_name(); String username = generateUsername(firstName,
	  lastName); userDto.setIsActive(0); userDto.setCreated_date(new Date());
	  userDto.setUsername(username); userService.createUser(userDto);
	  
	  redirectAttributes.addFlashAttribute("flashMessage",
	  "Your information saved successfully!"); 
	  return "redirect:/medvault/user/signup"; 
	  } 
	  catch (Exception e) {
	  
	  e.printStackTrace(); model.addAttribute("model", userDto);
	  model.addAttribute("failureMessage", "An error occurred while signing up.");
	  return "admin/signup"; 
	  }
	  }

	public static String generateUsername(String firstName, String lastName) {
		String cleanedFirstName = StringUtils.trimAllWhitespace(firstName).toLowerCase();
		String cleanedLastName = StringUtils.trimAllWhitespace(lastName).toLowerCase();
		System.out.println(cleanedFirstName + ">>>>>>>>>>>>>>" + cleanedLastName);
		return cleanedFirstName + "." + cleanedLastName;
	}

	@GetMapping("/user/getalluser")
	public String getAllUsers(Model model) {
		List<UserDto> allUsers = userService.getAllUsers();
		model.addAttribute("users", allUsers);

		return "admin/manageuser";
	}

	@GetMapping("/user/{id}/edit")
	public String editUser(@PathVariable int id, Model model) {

		UserDto userById = userService.getUserById(id);
		model.addAttribute("user", userById);

		return "editUserForm"; //
	}

	@GetMapping("/user/{id}/delete")
	public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {

		userService.deleteUsers(id);
		redirectAttributes.addFlashAttribute("deleteSuccess", "User deleted successfully!");
		return "redirect:/medvault/user/getalluser";
	}
}
