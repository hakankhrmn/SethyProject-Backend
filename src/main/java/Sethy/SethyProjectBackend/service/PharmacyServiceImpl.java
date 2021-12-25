package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.Pharmacy;
import Sethy.SethyProjectBackend.model.dto.PharmacyDto;
import Sethy.SethyProjectBackend.repository.PharmacyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final ModelMapper modelMapper;

    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository, ModelMapper modelMapper) {
        this.pharmacyRepository = pharmacyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PharmacyDto getByPharmacyId(int pharmacyId) {
        Pharmacy pharmacy = pharmacyRepository.getByPharmacyId(pharmacyId);
        if (pharmacy == null){
            throw new NotFoundException("COULD NOT FOUND THE PHARMACY");
        }
        return modelMapper.map(pharmacy, PharmacyDto.class);
    }
}
