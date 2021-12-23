package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.User;
import Sethy.SethyProjectBackend.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   UserDto save(UserDto userDto);
   void updateResetPasswordToken(String token, String email);
   User getByResetPasswordToken(String token);
   User getUserByUserMail(String username);
   void updatePassword(User user, String newPassword);

}
