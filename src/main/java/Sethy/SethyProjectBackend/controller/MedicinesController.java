package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.MedicineDto;
import Sethy.SethyProjectBackend.model.dto.MedicineWithPharmacyDto;
import Sethy.SethyProjectBackend.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MedicinesController {

    private final MedicineService medicineService;

    public MedicinesController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<MedicineWithPharmacyDto> getByMedicineId(@PathVariable("medicineId") int medicineId){
        MedicineWithPharmacyDto medicineWithPharmacyDto = medicineService.getByMedicineId(medicineId);
        return new ResponseEntity<>(medicineWithPharmacyDto, HttpStatus.OK);
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<MedicineWithPharmacyDto>> getAllMedicines(){
        List<MedicineWithPharmacyDto> medicineWithPharmacyDto = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicineWithPharmacyDto, HttpStatus.OK);
    }

    @PostMapping("/medicine")
    public ResponseEntity<MedicineDto> createMedicine(@RequestBody MedicineDto medicineDto){
        try {
            MedicineDto newMedicineDto = medicineService.createMedicine(medicineDto);
            return new ResponseEntity<>(newMedicineDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((MedicineDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
