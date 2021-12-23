package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.Role;
import Sethy.SethyProjectBackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.getByRoleName(roleName);
    }

    @Override
    public void addRole(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        roleRepository.save(role);
    }
}
