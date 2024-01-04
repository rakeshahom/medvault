/**
 * 
 */
package com.medsell.MedVault.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsell.MedVault.Exception.ResourceNotFoundException;
import com.medsell.MedVault.dto.AddressDto;
import com.medsell.MedVault.entity.Address;
import com.medsell.MedVault.repo.AddressRepo;
import com.medsell.MedVault.service.AddressService;

/**
 * 
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AddressRepo addressRepo;

	@Override
	public AddressDto createAddress(AddressDto addressDto) {
		Address address = this.dtoToAddress(addressDto);
		Address saveaddress = this.addressRepo.save(address);
		AddressDto addressToDto = this.addressToDto(saveaddress);
		return addressToDto;
	}

	
	@Override
	public AddressDto updateAddress(AddressDto addressDto, Long id) {
		Address address = this.addressRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		
		address.setStreet(addressDto.getStreet());
		address.setStreet2(addressDto.getStreet2());
		address.setCity(addressDto.getCity());
		address.setCountry(addressDto.getCountry());
		address.setIsActive(addressDto.getIsActive());
		address.setLongitude(addressDto.getLongitude());
		address.setLatitude(addressDto.getLatitude());
		address.setPrimary(addressDto.isPrimary());
		address.setPostalCode(addressDto.getPostalCode());
		address.setAddressType(addressDto.getAddressType().SECONDARY);
		
		Address updateaddress = this.addressRepo.save(address);
		AddressDto addressDTO2 = this.addressToDto(updateaddress);
		return addressDTO2;
	}

	@Override
	public AddressDto getAddressById(Long id) {
		Address address = this.addressRepo.findById(id).get();
		AddressDto addressToDto = this.addressToDto(address);
		return addressToDto;
	}

	@Override
	public List<AddressDto> getAllAddress() {
		List<Address> users = this.addressRepo.findAll();
		List<AddressDto> collect = users.stream().map(user->this.addressToDto(user)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public void deleteAddress(Long id) {
		Address address = this.addressRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Address","Id",id));
		this.addressRepo.delete(address);
	}

	@Override
	public void updateAddressIsActive(Long addressId, String isActive) {

		this.addressRepo.updatestatus(addressId, isActive);

	}

	private Address dtoToAddress(AddressDto addressDto) {
		Address map = this.modelMapper.map(addressDto, Address.class);
		return map;
	}

	private AddressDto addressToDto(Address address) {
		AddressDto map = this.modelMapper.map(address, AddressDto.class);
		return map;

	}

}
