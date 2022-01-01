package Sethy.SethyProjectBackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistWithPharmacyDto {

    private int pharmacistId;

    private PharmacyDto pharmacy;
}
