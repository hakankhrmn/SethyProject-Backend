package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.dto.PharmacyDto;

public interface PharmacyService {
    PharmacyDto getByPharmacyId(int pharmacyId);
}
