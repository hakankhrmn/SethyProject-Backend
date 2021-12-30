package Sethy.SethyProjectBackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineWithPharmacyDto {

    private int medicineId;

    private String medicineName;

    private String medicineExpireDate;

    private String medicineDescription;

    private List<PharmacyDto> medicinePharmacies;

}
