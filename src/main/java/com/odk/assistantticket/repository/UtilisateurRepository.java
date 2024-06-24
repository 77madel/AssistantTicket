package com.odk.assistantticket.repository;


import com.odk.assistantticket.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
   Utilisateur findByEmail(String email);

    @Query("SELECT u FROM Utilisateur u JOIN u.role r WHERE r.libelle = :roleLibelle")
    List<Utilisateur> findByRoleLibelle(@Param("roleLibelle") String roleLibelle);
}
