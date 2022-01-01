package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.User;
import Sethy.SethyProjectBackend.model.dto.PharmacistInputDto;
import Sethy.SethyProjectBackend.model.dto.UserWithPharmacistDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   UserWithPharmacistDto save(PharmacistInputDto pharmacistInputDto);
   void updateResetPasswordToken(String token, String email);
   User getByResetPasswordToken(String token);
   User getUserByUserMail(String username);
   void updatePassword(User user, String newPassword);

}
