package com.medsell.MedVault.service;

import java.util.List;

import com.medsell.MedVault.dto.MedicineDetailsDto;

public interface MedicineDetailService {

	MedicineDetailsDto createMedicineDetail(MedicineDetailsDto medicineDetail);

	boolean medicineDetailExist(String medicineDetail);

	MedicineDetailsDto updateMedicineDetail(MedicineDetailsDto medicineDetailDto, int medicineDetailId);

	MedicineDetailsDto getMedicineDetailById(int medicineDetailId);

	List<MedicineDetailsDto> getAllMedicineDetails();

	void deleteMedicineDetails(int medicineDetailId);
}
