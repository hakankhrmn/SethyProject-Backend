package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.dto.PharmacistDto;

public interface PharmacistService {

    PharmacistDto getPharmacistByUser(String userMail);
    void deleteAllPharmacists();
}
