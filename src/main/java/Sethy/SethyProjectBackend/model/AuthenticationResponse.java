package Sethy.SethyProjectBackend.model;


import Sethy.SethyProjectBackend.model.dto.UserWithPharmacistDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserWithPharmacistDto userDto;
}
