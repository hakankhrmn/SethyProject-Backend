package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.exception.AlreadyExistsException;
import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.Medicine;
import Sethy.SethyProjectBackend.model.dto.MedicineDto;
import Sethy.SethyProjectBackend.repository.MedicineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, ModelMapper modelMapper) {
        this.medicineRepository = medicineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicineDto getByMedicineId(int medicineId) {
        Medicine medicine;
        if (medicineRepository.findById(medicineId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE QUESTION");
        }
        medicine = medicineRepository.findById(medicineId).get();
        return modelMapper.map(medicine,MedicineDto.class);
    }

    @Override
    public MedicineDto createMedicine(MedicineDto medicineDto) {
        Medicine medicine =medicineRepository.getByMedicineName(medicineDto.getMedicineName());
        if (medicine!=null){
            throw new AlreadyExistsException("MEDICINE ALREADY EXISTS");
        }
        Medicine newMedicine = new Medicine();
        newMedicine.setMedicineName(medicineDto.getMedicineName());
        newMedicine.setMedicineExpireDate(medicineDto.getMedicineExpireDate());
        newMedicine.setMedicineDescription(medicineDto.getMedicineDescription());
        return modelMapper.map(medicineRepository.save(newMedicine),MedicineDto.class);
    }

    @Override
    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return medicines.stream().map(medicine -> modelMapper.map(medicine,MedicineDto.class)).collect(Collectors.toList());
    }


}
