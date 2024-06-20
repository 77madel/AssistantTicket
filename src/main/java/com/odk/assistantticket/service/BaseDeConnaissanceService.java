package com.odk.assistantticket.service;

import com.odk.assistantticket.model.BaseDeConnaissance;
import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.BaseDeConnaissanceRepository;
import com.odk.assistantticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BaseDeConnaissanceService {

    private BaseDeConnaissanceRepository baseDeConnaissanceRepository;
    private UtilisateurRepository utilisateurRepository;

    public Iterable<BaseDeConnaissance> listAllBaseDeConnaissance() {
        return baseDeConnaissanceRepository.findAll();
    }

    public Optional<BaseDeConnaissance> getBaseDeConnaissanceById(long id) {
        return baseDeConnaissanceRepository.findById(id);
    }

    public void insertBaseDeConnaissance(BaseDeConnaissance baseDeConnaissance) {
        // Récupère l'utilisateur authentifié
        Utilisateur  utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        baseDeConnaissance.setUtilisateur(utilisateur);

        baseDeConnaissance.setUtilisateur(utilisateur);
        baseDeConnaissanceRepository.save(baseDeConnaissance);
    }


    //Modification Categorie
    public BaseDeConnaissance updateBase(Long id, BaseDeConnaissance updateBase) {
        BaseDeConnaissance existingBase = baseDeConnaissanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Base de connaissance non trouvé: " + id));

        // Mettre à jour les champs de la basedeconnaissance existante avec les nouvelles valeurs
        existingBase.setName(updateBase.getName());
        existingBase.setLink(updateBase.getLink());

        // Vérifier et mettre à jour l'utilisateur associé à la basedeconnaissance
        if (updateBase.getUtilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(updateBase.getUtilisateur().getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + updateBase.getUtilisateur().getId()));
            existingBase.setUtilisateur(utilisateur);
        } else {
            existingBase.setUtilisateur(null);
        }

        // Sauvegarder la basedeconnaissance mise à jour et retourner l'entité mise à jour
        return baseDeConnaissanceRepository.save(existingBase);
    }

    public void supprimerBase(Long id) {
        BaseDeConnaissance existingBase = baseDeConnaissanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Base de connaissance non trouvé: " + id));
        baseDeConnaissanceRepository.delete(existingBase);
    }
}
