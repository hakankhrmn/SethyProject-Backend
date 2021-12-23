package Sethy.SethyProjectBackend.model.dto;

import Sethy.SethyProjectBackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistDto {
    private User user;
}
