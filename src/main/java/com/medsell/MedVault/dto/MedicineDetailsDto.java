/**
 * 
 */
package com.medsell.MedVault.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.medsell.MedVault.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDetailsDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String manufacturer;
	private Double price;
	private double quantity;
	private String expiry_date;
	private int isActive;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
	

}