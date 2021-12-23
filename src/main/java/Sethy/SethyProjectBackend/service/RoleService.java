package Sethy.SethyProjectBackend.service;

import Sethy.SethyProjectBackend.model.Role;

public interface RoleService {
    Role getByRoleName(String roleName);
    void addRole(String roleName);
}
