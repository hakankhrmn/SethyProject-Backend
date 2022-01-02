package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.Pharmacist;
import Sethy.SethyProjectBackend.model.dto.PharmacistWithPharmacyDto;
import Sethy.SethyProjectBackend.repository.PharmacistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PharmacistServiceImpl(PharmacistRepository pharmacistRepository, UserService userService, ModelMapper modelMapper) {
        this.pharmacistRepository = pharmacistRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public PharmacistWithPharmacyDto getPharmacistByUser(String userMail) {
        Pharmacist pharmacist = pharmacistRepository.getPharmacistByUser(userService.getUserByUserMail(userMail));
        if(pharmacist == null){
            throw new NotFoundException("COULD NOT FOUND THE PHARMACIST");
        }
        return modelMapper.map(pharmacist, PharmacistWithPharmacyDto.class);
    }

    @Override
    public List<PharmacistWithPharmacyDto> getAllPharmacists() {
        List<Pharmacist> pharmacists = pharmacistRepository.findAll();
        return pharmacists.stream().map(pharmacist -> modelMapper.map(pharmacist, PharmacistWithPharmacyDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAllPharmacists() {
        pharmacistRepository.deleteAll();
    }
}
