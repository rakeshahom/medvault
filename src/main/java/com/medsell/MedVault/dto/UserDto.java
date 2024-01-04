package com.medsell.MedVault.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.medsell.MedVault.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
	private int id;
	@Column(name = "firstName")
	private String first_name;
	@Column(name = "lastName")
	private String last_name;
	@Column(name = "email")
	private String email;

	@Column(name = "userName")
	private String username;

	@Column(name = "userProfileImage")
	private String user_profile_image;

	@Column(name = "password")
	private String password;
	private int isActive;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Address_id", referencedColumnName = "id"))
	private List<AddressDto> address;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

}
