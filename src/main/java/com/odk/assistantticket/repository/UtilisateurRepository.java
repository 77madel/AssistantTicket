package com.odk.assistantticket.repository;


import com.odk.assistantticket.enums.TypeRole;
import com.odk.assistantticket.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
   Utilisateur findByEmail(String email);

}
