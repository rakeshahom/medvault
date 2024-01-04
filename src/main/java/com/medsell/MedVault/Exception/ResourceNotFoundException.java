/**
 * 
 */
package com.medsell.MedVault.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private long feildValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long feildValue) {
		super(String.format("%s not found with %s :%s", resourceName, feildValue, feildValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.feildValue = feildValue;
	}

}
