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
import com.medsell.MedVault.dto.MedicineDetailsDto;
import com.medsell.MedVault.entity.MedicineDetails;
import com.medsell.MedVault.repo.MedicineDetailRepo;
import com.medsell.MedVault.repo.RoleRepo;
import com.medsell.MedVault.service.MedicineDetailService;
import com.medsell.MedVault.service.RoleService;

/**
 * 
 */
@Service
public class MedicineDetailServiceImpl implements MedicineDetailService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MedicineDetailRepo medicineDetailRepo;

	@Override
	public MedicineDetailsDto createMedicineDetail(MedicineDetailsDto medicineDetailDto) {
		MedicineDetails medicineDetails = this.dtoToMedicineDetail(medicineDetailDto);
		MedicineDetails saverole = this.medicineDetailRepo.save(medicineDetails);
		MedicineDetailsDto MedicineDetailToDto = this.MedicineDetailToDto(saverole);
		return MedicineDetailToDto;
	}

	@Override
	public boolean medicineDetailExist(String medicineDetails) {
		
		return findByName(medicineDetails).isPresent();
	}
	public Optional<MedicineDetails>findByName(String medicineDetails){
		return  this.medicineDetailRepo.findByName(medicineDetails);
	}

	@Override
	public MedicineDetailsDto updateMedicineDetail(MedicineDetailsDto medicineDetailDto, int medicineDetailId) {
		MedicineDetails medicineDetails = this.medicineDetailRepo.findById(medicineDetailId)
				.orElseThrow(() -> new ResourceNotFoundException("MedicineDetails", "Id", medicineDetailId));

		medicineDetails.setName(medicineDetailDto.getName());
		medicineDetails.setManufacturer(medicineDetailDto.getManufacturer());
		
		medicineDetails.setPrice(medicineDetailDto.getPrice());
		medicineDetails.setQuantity(medicineDetailDto.getQuantity());
		medicineDetails.setExpiry_date(medicineDetailDto.getExpiry_date());
		medicineDetails.setIsActive(medicineDetailDto.getIsActive());
		
		MedicineDetails updaterole = this.medicineDetailRepo.save(medicineDetails);
		MedicineDetailsDto roleDTO2 = this.MedicineDetailToDto(updaterole);
		return roleDTO2;
	}

	@Override
	public MedicineDetailsDto getMedicineDetailById(int medicineDetailId) {
		MedicineDetails medicineDetails = this.medicineDetailRepo.findById(medicineDetailId).get();
		MedicineDetailsDto MedicineDetailToDto = this.MedicineDetailToDto(medicineDetails);
		return MedicineDetailToDto;
	}

	@Override
	public List<MedicineDetailsDto> getAllMedicineDetails() {
		List<MedicineDetails> roles = this.medicineDetailRepo.findAll();
		List<MedicineDetailsDto> collect = roles.stream().map(medicineDetails->this.MedicineDetailToDto(medicineDetails)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public void deleteMedicineDetails(int medicineDetailId) {
		MedicineDetails medicineDetails = this.medicineDetailRepo.findById(medicineDetailId).orElseThrow(()->new ResourceNotFoundException("Roles","Id",medicineDetailId));
		this.medicineDetailRepo.delete(medicineDetails);
	}

//	@Override
//	public boolean updateUserstatus(String status,Integer userId) {
//
//		boolean updatestatus = this.userRepo.updatestatus(status,userId);
//		return updatestatus;
//
//	}

	private MedicineDetails dtoToMedicineDetail(MedicineDetailsDto medicineDetailDto) {
		MedicineDetails map = this.modelMapper.map(medicineDetailDto, MedicineDetails.class);
		return map;
	}

	private MedicineDetailsDto MedicineDetailToDto(MedicineDetails medicineDetails) {
		MedicineDetailsDto map = this.modelMapper.map(medicineDetails, MedicineDetailsDto.class);
		return map;

	}


}
