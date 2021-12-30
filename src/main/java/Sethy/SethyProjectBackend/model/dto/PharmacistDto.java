package Sethy.SethyProjectBackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistDto {

    private int pharmacistId;

    private PharmacyDto pharmacy;
}
