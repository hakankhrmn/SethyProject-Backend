package Sethy.SethyProjectBackend.controller;

import Sethy.SethyProjectBackend.model.dto.MedicineDto;
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
    public ResponseEntity<MedicineDto> getByMedicineId(@PathVariable("medicineId") int medicineId){
        MedicineDto medicineDto = medicineService.getByMedicineId(medicineId);
        return new ResponseEntity<>(medicineDto, HttpStatus.OK);
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<MedicineDto>> getAllMedicines(){
        List<MedicineDto> medicineDtos = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicineDtos, HttpStatus.OK);
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
