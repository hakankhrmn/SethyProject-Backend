package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.dto.PharmacyDto;

import java.util.List;

public interface PharmacyService {
    PharmacyDto getByPharmacyId(int pharmacyId);
    List<PharmacyDto> getAllPharmacies();
    List<PharmacyDto> getMedicinePharmacies(int medicineId);
    void deleteAllPharmacies();
    void deletePharmacy(int pharmacyId);
}
