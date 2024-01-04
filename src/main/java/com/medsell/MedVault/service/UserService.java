package com.medsell.MedVault.service;

import java.util.List;

import com.medsell.MedVault.dto.UserDto;


public interface UserService {

UserDto createUser(UserDto user);
	

 boolean userExist(String username);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(int userId);

	List<UserDto> getAllUsers();
	
	int userCount();
	double calculateActiveUserPercentage();
	void deleteUsers(int userId);
//	boolean updateUserstatus(String status,Integer userId);

	
}
