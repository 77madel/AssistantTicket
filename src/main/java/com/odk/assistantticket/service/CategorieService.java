package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.CategorieRepository;
import com.odk.assistantticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CategorieService {

    private CategorieRepository categorieRepository;
    private UtilisateurRepository utilisateurRepository;

    //Trouvé Categorie
    public Iterable<Categorie> getAllCategories() {

        return categorieRepository.findAll();
    }

    //Ajouté Categorie
    public void save(Categorie categorie) {
        //Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // categorie.setUtilisateur(utilisateur);
        categorieRepository.save(categorie);
    }

    //Trouvé Categorie par Id
    public Optional<Categorie> getCategorieById(int id) {

        return categorieRepository.findById(id);
    }

    //Modification Categorie
    public Categorie updateCategorie(Integer id, Categorie updatedCategorie) {
        Categorie existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvé: " + id));

        // Mettre à jour les champs de la catégorie existante avec les nouvelles valeurs
        existingCategorie.setName(updatedCategorie.getName());

        // Vérifier et mettre à jour l'utilisateur associé à la catégorie
        if (updatedCategorie.getUtilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(updatedCategorie.getUtilisateur().getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + updatedCategorie.getUtilisateur().getId()));
            existingCategorie.setUtilisateur(utilisateur);
        } else {
            existingCategorie.setUtilisateur(null);
        }

        // Sauvegarder la catégorie mise à jour et retourner l'entité mise à jour
        return categorieRepository.save(existingCategorie);
    }

    //Supprimé Categorie
    public void deleteCategorie(int id) {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new RuntimeException("L'Id catégoie non trouvé: " + id));
        categorieRepository.delete(categorie);
    }
}
