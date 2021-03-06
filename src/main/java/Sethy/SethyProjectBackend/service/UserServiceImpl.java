package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.Pharmacist;
import Sethy.SethyProjectBackend.model.Pharmacy;
import Sethy.SethyProjectBackend.model.Role;
import Sethy.SethyProjectBackend.model.User;
import Sethy.SethyProjectBackend.model.dto.PharmacistInputDto;
import Sethy.SethyProjectBackend.model.dto.UserDto;
import Sethy.SethyProjectBackend.model.dto.UserWithPharmacistDto;
import Sethy.SethyProjectBackend.repository.PharmacistRepository;
import Sethy.SethyProjectBackend.repository.PharmacyRepository;
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
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PharmacyRepository pharmacyRepository;
    private final PharmacistRepository pharmacistRepository;
    private final PasswordEncoder bcryptEncoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PharmacyRepository pharmacyRepository, PharmacistRepository pharmacistRepository, PasswordEncoder bcryptEncoder, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacistRepository = pharmacistRepository;
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
    public UserDto save(PharmacistInputDto pharmacistInputDto) {

        Role adminUser = roleService.getByRoleName("ADMIN_USER");
        Role pharmacistUser = roleService.getByRoleName("PHARMACIST_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(pharmacistUser);
        User newUser = new User();
        newUser.setUserName(pharmacistInputDto.getUserName());
        newUser.setUserSurname(pharmacistInputDto.getUserSurname());
        newUser.setUserMail(pharmacistInputDto.getUserMail());
        newUser.setUserPassword(bcryptEncoder.encode(pharmacistInputDto.getUserPassword()));
        newUser.setUserRoles(roles);

        Pharmacy newPharmacy = new Pharmacy();
        newPharmacy.setLocationLatitude(pharmacistInputDto.getLocationLatitude());
        newPharmacy.setLocationLongitude(pharmacistInputDto.getLocationLongitude());
        newPharmacy.setPharmacyName(pharmacistInputDto.getPharmacyName());
        newPharmacy.setPharmacyPhone(pharmacistInputDto.getPharmacyPhone());


        Pharmacist newPharmacist = new Pharmacist();
        newPharmacist.setUser(newUser);
        newPharmacist.setPharmacy(newPharmacy);

        newUser.setPharmacist(newPharmacist);
        newPharmacy.setPharmacyOwner(newPharmacist);

        return modelMapper.map(userRepository.save(newUser), UserDto.class);
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
        User user = userRepository.getUserByUserMail(username);
        if(user==null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return user;
    }

    @Override
    public List<UserWithPharmacistDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> modelMapper.map(user, UserWithPharmacistDto.class)).collect(Collectors.toList());
    }

    @Override
    public void updatePassword(User user, String newPassword) {

        String encodedPassword = bcryptEncoder.encode(newPassword);
        user.setUserPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public void deleteAllUsers() {

        List<User> users = userRepository.findAll();
        for (User user : users) {
            Set<Role> roles = user.getUserRoles();
            if (roles.isEmpty()){
                userRepository.delete(user);
            }
            for (Role role : roles) {
                if (role.getRoleName().equals("PHARMACIST_USER")){
                    userRepository.delete(user);
                }
            }
        }
    }

}


