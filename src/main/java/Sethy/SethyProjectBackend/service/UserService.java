package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.User;
import Sethy.SethyProjectBackend.model.dto.PharmacistInputDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   PharmacistInputDto save(PharmacistInputDto pharmacistInputDto);
   void updateResetPasswordToken(String token, String email);
   User getByResetPasswordToken(String token);
   User getUserByUserMail(String username);
   void updatePassword(User user, String newPassword);

}
