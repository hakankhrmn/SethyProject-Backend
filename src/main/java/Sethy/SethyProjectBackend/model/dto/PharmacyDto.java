package Sethy.SethyProjectBackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyDto {

    private int pharmacyId;

    private String pharmacyName;

    private String pharmacyPhone;

    private double locationLatitude;

    private double locationLongitude;

}
