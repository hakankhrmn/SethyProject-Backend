package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.User;
import Sethy.SethyProjectBackend.model.dto.PharmacistInputDto;
import Sethy.SethyProjectBackend.model.dto.UserDto;
import Sethy.SethyProjectBackend.model.dto.UserWithPharmacistDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
   UserDto save(PharmacistInputDto pharmacistInputDto);
   void updateResetPasswordToken(String token, String email);
   User getByResetPasswordToken(String token);
   User getUserByUserMail(String username);
   List<UserWithPharmacistDto> getAllUsers();
   void updatePassword(User user, String newPassword);
   void deleteAllUsers();

}
