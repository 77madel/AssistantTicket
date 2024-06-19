package com.odk.assistantticket.repository;


import com.odk.assistantticket.model.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
   Optional<Utilisateur> findByEmail(String email);
}
