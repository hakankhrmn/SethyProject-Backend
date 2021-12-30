package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.Pharmacy;
import Sethy.SethyProjectBackend.model.dto.PharmacyDto;
import Sethy.SethyProjectBackend.repository.PharmacyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).get();
        if (pharmacy == null){
            throw new NotFoundException("COULD NOT FOUND THE PHARMACY");
        }

        return modelMapper.map(pharmacy, PharmacyDto.class);
    }

    @Override
    public List<PharmacyDto> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
        return pharmacies.stream().map(pharmacy -> modelMapper.map(pharmacy, PharmacyDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAllPharmacies() {
        pharmacyRepository.deleteAll();
    }

    @Override
    public void deletePharmacy(int pharmacyId) {

        if (pharmacyRepository.findById(pharmacyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE PHARMACY");
        }
        pharmacyRepository.deleteById(pharmacyId);

    }
}
