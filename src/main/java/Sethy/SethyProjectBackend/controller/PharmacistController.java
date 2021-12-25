package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.PharmacistDto;
import Sethy.SethyProjectBackend.model.dto.PharmacistWithMail;
import Sethy.SethyProjectBackend.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PharmacistController {

    private final PharmacistService pharmacistService;

    @Autowired
    public PharmacistController(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping("/pharmacist")
    public ResponseEntity<PharmacistDto> getPharmacistByUser(@RequestBody PharmacistWithMail pharmacist){
        try {
            PharmacistDto pharmacistDto = pharmacistService.getPharmacistByUser(pharmacist.getUserMail());
            return new ResponseEntity<>(pharmacistDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((PharmacistDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
