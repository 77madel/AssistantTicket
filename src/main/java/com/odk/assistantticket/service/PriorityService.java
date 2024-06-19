package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Priorite;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.PrioriteRepository;
import com.odk.assistantticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PriorityService {

    private PrioriteRepository prioriteRepository;
    private UtilisateurRepository utilisateurRepository;

    public Iterable<Priorite> getAllPriorite() {

        return prioriteRepository.findAll();
    }

    public void AddPriorite(Priorite priorite) {
        //Utilisateur  utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // priorite.setUtilisateur(utilisateur);
        prioriteRepository.save(priorite);
    }

    //Trouvé Prioriter par Id
    public Optional<Priorite> getPrioriteById(Long id) {

        return prioriteRepository.findById(id);
    }

    //Modification Priorité
    public Priorite updatedPriorite(Long id, Priorite updatedPriorite) {
        Priorite existingPriorite = prioriteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie not found with id: " + id));

        // Mettre à jour les champs de la priorite existante avec les nouvelles valeurs
        existingPriorite.setName(updatedPriorite.getName());

        // Vérifier et mettre à jour l'utilisateur associé à la prioriter
        if (updatedPriorite.getUtilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(updatedPriorite.getUtilisateur().getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur not found with id: " + updatedPriorite.getUtilisateur().getId()));
            existingPriorite.setUtilisateur(utilisateur);
        } else {
            existingPriorite.setUtilisateur(null);
        }

        // Sauvegarder la priorite mise à jour et retourner l'entité mise à jour
        return prioriteRepository.save(existingPriorite);
    }

    //Supprimé Priorité
    public void deletePriorite(long id) {
        Priorite priorite = prioriteRepository.findById(id).orElseThrow(() -> new RuntimeException("L'Id prioriter non trouvé: " + id));
        prioriteRepository.delete(priorite);
    }
}
