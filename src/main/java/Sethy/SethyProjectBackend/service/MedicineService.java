package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.dto.MedicineDto;
import Sethy.SethyProjectBackend.model.dto.MedicineWithPharmacyDto;

import java.util.List;

public interface MedicineService {
    MedicineWithPharmacyDto getByMedicineId(int medicineId);
    MedicineDto createMedicine(int pharmacyId, MedicineDto medicineDto);
    List<MedicineWithPharmacyDto> getAllMedicines();
    void deleteMedicine(int medicineId);
    void deleteAllMedicines();

}
