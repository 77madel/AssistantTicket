package com.odk.assistantticket.repository;

import com.odk.assistantticket.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByLibelle(String libelle);
}
