package Sethy.SethyProjectBackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistInputDto {

    private int pharmacistId;

    private String userName;

    private String userSurname;

    private String userMail;

    private String userPassword;

    private String pharmacyName;

    private double locationLatitude;

    private double locationLongitude;

    private String pharmacyPhone;

}
