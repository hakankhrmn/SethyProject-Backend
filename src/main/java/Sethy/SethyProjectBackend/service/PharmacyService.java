package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.dto.PharmacyDto;

import java.util.List;

public interface PharmacyService {
    PharmacyDto getByPharmacyId(int pharmacyId);
    List<PharmacyDto> getAllPharmacies();
}
