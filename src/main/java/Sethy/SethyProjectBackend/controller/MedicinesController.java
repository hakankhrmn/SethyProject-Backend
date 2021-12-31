package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.MedicineDto;
import Sethy.SethyProjectBackend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MedicinesController {

    private final MedicineService medicineService;

    @Autowired
    public MedicinesController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<MedicineDto> getByMedicineId(@PathVariable("medicineId") int medicineId){
        MedicineDto medicineDto = medicineService.getByMedicineId(medicineId);
        return new ResponseEntity<>(medicineDto, HttpStatus.OK);
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<MedicineDto>> getAllMedicines(){
        List<MedicineDto> medicineDto = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicineDto, HttpStatus.OK);
    }

    @PostMapping("/medicine/{pharmacyId}")
    public ResponseEntity<MedicineDto> createMedicine(@PathVariable("pharmacyId") int pharmacyId,@RequestBody MedicineDto medicineDto){
        try {
            MedicineDto newMedicineDto = medicineService.createMedicine(pharmacyId,medicineDto);
            return new ResponseEntity<>(newMedicineDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((MedicineDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/medicine/{medicineId}")
    public ResponseEntity<HttpStatus> deleteMedicine(@PathVariable("medicineId") int medicineId){
        try {
            medicineService.deleteMedicine(medicineId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
