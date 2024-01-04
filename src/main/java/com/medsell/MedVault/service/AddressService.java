package com.medsell.MedVault.service;

import java.util.List;

import com.medsell.MedVault.dto.AddressDto;

public interface AddressService {

	AddressDto createAddress(AddressDto address);

	// boolean userexists(String username);

	AddressDto updateAddress(AddressDto address, Long id);

	AddressDto getAddressById(Long id);

	List<AddressDto> getAllAddress();

	void deleteAddress(Long id);

	void updateAddressIsActive(Long id, String isacive);

}
