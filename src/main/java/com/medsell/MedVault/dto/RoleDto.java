package com.medsell.MedVault.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.medsell.MedVault.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "permissions")
	private String permissions;
	private String isActive;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	@OneToMany(mappedBy = "role")
	private List<User> users;

}
