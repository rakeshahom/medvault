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
import com.medsell.MedVault.dto.RoleDto;
import com.medsell.MedVault.entity.Role;
import com.medsell.MedVault.repo.RoleRepo;
import com.medsell.MedVault.service.RoleService;

/**
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public RoleDto createRole(RoleDto roleDto) {
		Role role = this.dtoToRole(roleDto);
		Role saverole = this.roleRepo.save(role);
		RoleDto roleToDto = this.roleToDto(saverole);
		return roleToDto;
	}

	@Override
	public boolean roleExist(String role) {
		
		return findUserByName(role).isPresent();
	}
	public Optional<Role>findUserByName(String role){
		return  this.roleRepo.findByName(role);
	}

	@Override
	public RoleDto updateRole(RoleDto roleDto, int roleId) {
		Role role = this.roleRepo.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));

		role.setName(roleDto.getName());
		role.setPermissions(roleDto.getPermissions());
		role.setDescription(roleDto.getDescription());
		role.setIsActive(roleDto.getIsActive());
		
		Role updaterole = this.roleRepo.save(role);
		RoleDto roleDTO2 = this.roleToDto(updaterole);
		return roleDTO2;
	}

	@Override
	public RoleDto getRoleById(int roleId) {
		Role role = this.roleRepo.findById(roleId).get();
		RoleDto roleToDto = this.roleToDto(role);
		return roleToDto;
	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> roles = this.roleRepo.findAll();
		List<RoleDto> collect = roles.stream().map(role->this.roleToDto(role)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public void deleteRoles(int roleId) {
		Role role = this.roleRepo.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Roles","Id",roleId));
		this.roleRepo.delete(role);
	}

//	@Override
//	public boolean updateUserstatus(String status,Integer userId) {
//
//		boolean updatestatus = this.userRepo.updatestatus(status,userId);
//		return updatestatus;
//
//	}

	private Role dtoToRole(RoleDto roleDto) {
		Role map = this.modelMapper.map(roleDto, Role.class);
		return map;
	}

	private RoleDto roleToDto(Role role) {
		RoleDto map = this.modelMapper.map(role, RoleDto.class);
		return map;

	}


}
