package Sethy.SethyProjectBackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPharmacistDto {

    private String userName;
    private String userSurname;
    private String userMail;
    private String userPassword;
    private PharmacistWithPharmacyDto pharmacist;
    private List<RoleDto> userRoles;

}
