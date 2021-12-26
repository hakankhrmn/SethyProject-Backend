package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.service.MedicineService;
import Sethy.SethyProjectBackend.service.PharmacistService;
import Sethy.SethyProjectBackend.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {
    private final MedicineService medicineService;
    private final PharmacyService pharmacyService;
    private final PharmacistService pharmacistService;

    @Autowired
    public AdminController(MedicineService medicineService, PharmacyService pharmacyService, PharmacistService pharmacistService) {
        this.medicineService = medicineService;
        this.pharmacyService = pharmacyService;
        this.pharmacistService = pharmacistService;
    }

    @DeleteMapping("/deleteAllMedicines")
    public ResponseEntity<HttpStatus> deleteAllMedicines(){
        try {
            medicineService.deleteAllMedicines();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllPharmacies")
    public ResponseEntity<HttpStatus> deleteAllPharmacies(){
        try {
            pharmacyService.deleteAllPharmacies();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllPharmacists")
    public ResponseEntity<HttpStatus> deleteAllPharmacists(){
        try {
            pharmacistService.deleteAllPharmacists();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
