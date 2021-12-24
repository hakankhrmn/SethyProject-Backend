package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.Pharmacist;
import Sethy.SethyProjectBackend.model.Role;
import Sethy.SethyProjectBackend.model.User;
import Sethy.SethyProjectBackend.model.dto.PharmacistDto;
import Sethy.SethyProjectBackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bcryptEncoder, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        User user = userRepository.getUserByUserMail(userMail);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        for(Role role : user.getUserRoles()){
            roles.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserMail(), user.getUserPassword(), roles);
    }

    @Override
    public PharmacistDto save(PharmacistDto pharmacistDto) {

        Role adminUser = roleService.getByRoleName("ADMIN_USER");
        Role pharmacistUser = roleService.getByRoleName("PHARMACIST_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(pharmacistUser);
        User newUser = new User();
        newUser.setUserName(pharmacistDto.getUserName());
        newUser.setUserSurname(pharmacistDto.getUserSurname());
        newUser.setUserMail(pharmacistDto.getUserMail());
        newUser.setUserPassword(bcryptEncoder.encode(pharmacistDto.getUserPassword()));
        newUser.setUserRoles(roles);
        Pharmacist newPharmacist = new Pharmacist();
        newPharmacist.setUser(newUser);

        userRepository.save(newUser);



        return pharmacistDto;
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.getUserByUserMail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new NotFoundException("Could not find any customer with the email " + email);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.getByResetPasswordToken(token);
    }

    @Override
    public User getUserByUserMail(String username) {
        return userRepository.getUserByUserMail(username);
    }

    @Override
    public void updatePassword(User user, String newPassword) {

        String encodedPassword = bcryptEncoder.encode(newPassword);
        user.setUserPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

}


