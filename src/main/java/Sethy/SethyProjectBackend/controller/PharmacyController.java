package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.PharmacyDto;
import Sethy.SethyProjectBackend.model.dto.PharmacyWithId;
import Sethy.SethyProjectBackend.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/pharmacy")
    public ResponseEntity<PharmacyDto> getPharmacistByUser(@RequestBody PharmacyWithId pharmacyWithId){
        try {
            PharmacyDto pharmacyDto = pharmacyService.getByPharmacyId(pharmacyWithId.getPharmacyId());
            return new ResponseEntity<>(pharmacyDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((PharmacyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pharmacies")
    public ResponseEntity<List<PharmacyDto>> getAllPharmacies(){
        try {
            List<PharmacyDto> pharmacyDtos = pharmacyService.getAllPharmacies();
            return new ResponseEntity<>(pharmacyDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<PharmacyDto>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
