package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.dto.PharmacistDto;
import Sethy.SethyProjectBackend.model.dto.PharmacistWithPharmacyDto;

import java.util.List;

public interface PharmacistService {

    PharmacistDto getPharmacistByUser(String userMail);
    List<PharmacistWithPharmacyDto> getAllPharmacists();
    void deleteAllPharmacists();
}
