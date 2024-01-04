package com.medsell.MedVault.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.medsell.MedVault.enumType.AddressType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {
	
	private Long id;
	@Column(name = "street")
	private String street;
	@Column(name = "street2")
	private String street2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "postalCode")
	private String postalCode;
	@Column(name = "country")
	private String country;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "addressType")
	private AddressType addressType;
	@Column(name = "isPrimary")
	private boolean isPrimary;
	private String isActive;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
}
