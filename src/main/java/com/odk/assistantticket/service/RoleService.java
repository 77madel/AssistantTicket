package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Role;
import com.odk.assistantticket.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public Optional<Role> getRoleById(Long id) {
       return roleRepository.findById(id);
    }

    public Role updateRole(Long id, Role role) {
        Role roleUpdate = roleRepository.findById(id).orElse(null);
        assert roleUpdate != null;
        roleUpdate.setLibelle(role.getLibelle());
        return roleRepository.save(roleUpdate);
    }

    public void deleteRole(Long id) {
        Role roleToDelete = roleRepository.findById(id).orElse(null);
        assert roleToDelete != null;
        roleRepository.delete(roleToDelete);
    }
}
