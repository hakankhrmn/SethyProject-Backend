package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.PharmacyDto;
import Sethy.SethyProjectBackend.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/pharmacies")
@CrossOrigin
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyDto> getPharmacistById(@PathVariable int pharmacyId){
        try {
            PharmacyDto pharmacyDto = pharmacyService.getByPharmacyId(pharmacyId);
            return new ResponseEntity<>(pharmacyDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((PharmacyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<PharmacyDto>> getAllPharmacies(){
        try {
            List<PharmacyDto> pharmacyDtos = pharmacyService.getAllPharmacies();
            return new ResponseEntity<>(pharmacyDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<PharmacyDto>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<PharmacyDto>> getMedicinePharmacies(@PathVariable int medicineId){
        try {
            List<PharmacyDto> pharmacyDtos = pharmacyService.getMedicinePharmacies(medicineId);
            return new ResponseEntity<>(pharmacyDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<PharmacyDto>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity<HttpStatus> deleteMedicine(@PathVariable("pharmacyId") int pharmacyId){
        try {
            pharmacyService.deletePharmacy(pharmacyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
