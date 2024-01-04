package com.medsell.MedVault.service;

import java.util.List;

import com.medsell.MedVault.dto.RoleDto;


public interface RoleService {

RoleDto createRole(RoleDto role);
	

 boolean roleExist(String role);

 RoleDto updateRole(RoleDto user, int userId);

 RoleDto getRoleById(int userId);

	List<RoleDto> getAllRoles();

	void deleteRoles(int roleId);
//	boolean updateUserstatus(String status,Integer userId);

	
}
