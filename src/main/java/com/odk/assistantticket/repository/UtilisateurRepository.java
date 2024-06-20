package com.odk.assistantticket.repository;


import com.odk.assistantticket.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
   Utilisateur findByEmail(String email);
}
