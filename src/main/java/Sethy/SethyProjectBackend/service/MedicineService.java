package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    MedicineDto getByMedicineId(int medicineId);
    MedicineDto createMedicine(int pharmacyId, MedicineDto medicineDto);
    List<MedicineDto> getAllMedicines();
    void deleteMedicine(int medicineId);
    void deleteAllMedicines();

}
