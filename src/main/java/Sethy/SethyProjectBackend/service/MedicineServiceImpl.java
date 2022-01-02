package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.Medicine;
import Sethy.SethyProjectBackend.model.Pharmacy;
import Sethy.SethyProjectBackend.model.dto.MedicineDto;
import Sethy.SethyProjectBackend.repository.MedicineRepository;
import Sethy.SethyProjectBackend.repository.PharmacyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final PharmacyRepository pharmacyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, PharmacyRepository pharmacyRepository, ModelMapper modelMapper) {
        this.medicineRepository = medicineRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicineDto getByMedicineId(int medicineId) {
        Medicine medicine;
        if (medicineRepository.findById(medicineId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE MEDICINE");
        }
        medicine = medicineRepository.findById(medicineId).get();
        return modelMapper.map(medicine,MedicineDto.class);
    }

    @Override
    public MedicineDto createMedicine(int pharmacyId, MedicineDto medicineDto) {
        Medicine medicine =medicineRepository.getByMedicineName(medicineDto.getMedicineName());
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).get();
        if (medicine!=null){
            List<Pharmacy> pharmacies = medicine.getMedicinePharmacies();
            pharmacies.add(pharmacy);
            medicine.setMedicinePharmacies(pharmacies);

            List<Medicine> medicines = pharmacy.getPharmacyMedicines();
            medicines.add(medicine);
            pharmacy.setPharmacyMedicines(medicines);

            return modelMapper.map(medicineRepository.save(medicine),MedicineDto.class);
        }else{
            Medicine newMedicine = new Medicine();
            newMedicine.setMedicineName(medicineDto.getMedicineName());
            newMedicine.setMedicineImageUrl(medicineDto.getMedicineImageUrl());
            newMedicine.setMedicineDescription(medicineDto.getMedicineDescription());
            List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
            pharmacies.add(pharmacy);
            newMedicine.setMedicinePharmacies(pharmacies);

            List<Medicine> medicines = pharmacy.getPharmacyMedicines();
            medicines.add(newMedicine);
            pharmacy.setPharmacyMedicines(medicines);
            return modelMapper.map(medicineRepository.save(newMedicine),MedicineDto.class);
        }

    }

    @Override
    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return medicines.stream().map(medicine -> modelMapper.map(medicine,MedicineDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<MedicineDto> getPharmacyMedicine(int pharmacyId) {
        List<Medicine> medicines = pharmacyRepository.findById(pharmacyId).get().getPharmacyMedicines();
        return medicines.stream().map(medicine -> modelMapper.map(medicine, MedicineDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteMedicine(int medicineId) {
        if (medicineRepository.findById(medicineId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE MEDICINE");
        }
        medicineRepository.deleteById(medicineId);
    }

    @Override
    public void deleteAllMedicines() {
        medicineRepository.deleteAll();
    }


}
