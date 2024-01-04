/**
 * 
 */
package com.medsell.MedVault.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medcategory")
public class Category {

	/**
	 * 
	 */@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private int isActive;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<MedicineDetails> medicine = new ArrayList<>();
	    
}
