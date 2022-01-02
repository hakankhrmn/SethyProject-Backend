package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.MedicineDto;
import Sethy.SethyProjectBackend.model.dto.PharmacistWithPharmacyDto;
import Sethy.SethyProjectBackend.model.dto.PharmacyDto;
import Sethy.SethyProjectBackend.model.dto.UserWithPharmacistDto;
import Sethy.SethyProjectBackend.service.MedicineService;
import Sethy.SethyProjectBackend.service.PharmacistService;
import Sethy.SethyProjectBackend.service.PharmacyService;
import Sethy.SethyProjectBackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final MedicineService medicineService;
    private final PharmacyService pharmacyService;
    private final PharmacistService pharmacistService;
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserWithPharmacistDto>> getUsers(){
        List<UserWithPharmacistDto> userWithPharmacistDtos =  userService.getAllUsers();
        return new ResponseEntity<>(userWithPharmacistDtos,HttpStatus.OK);
    }

    @GetMapping("/pharmacists")
    public ResponseEntity<List<PharmacistWithPharmacyDto>> getAllPharmacists(){
        List<PharmacistWithPharmacyDto> pharmacistWithPharmacyDtos =  pharmacistService.getAllPharmacists();
        return new ResponseEntity<>(pharmacistWithPharmacyDtos,HttpStatus.OK);
    }

    @GetMapping("/pharmacies")
    public ResponseEntity<List<PharmacyDto>> getAllPharmacies(){
        List<PharmacyDto> pharmacyDtos =  pharmacyService.getAllPharmacies();
        return new ResponseEntity<>(pharmacyDtos,HttpStatus.OK);
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<MedicineDto>> getAllMedicines(){
        List<MedicineDto> medicineDtos=  medicineService.getAllMedicines();
        return new ResponseEntity<>(medicineDtos,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllMedicines")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteAllMedicines(){
        try {
            medicineService.deleteAllMedicines();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllPharmacies")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteAllPharmacies(){
        try {
            pharmacyService.deleteAllPharmacies();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllPharmacists")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteAllPharmacists(){
        try {
            pharmacistService.deleteAllPharmacists();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllUsers")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteAllUsers(){

        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
