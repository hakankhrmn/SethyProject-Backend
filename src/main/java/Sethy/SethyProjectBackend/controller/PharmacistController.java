package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.PharmacistDto;
import Sethy.SethyProjectBackend.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PharmacistController {

    private final PharmacistService pharmacistService;

    @Autowired
    public PharmacistController(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping("/pharmacist/{userMail}")
    public ResponseEntity<PharmacistDto> getPharmacistByUser(@RequestParam String userMail){
        try {
            PharmacistDto pharmacistDto = pharmacistService.getPharmacistByUser(userMail);
            return new ResponseEntity<>(pharmacistDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((PharmacistDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
