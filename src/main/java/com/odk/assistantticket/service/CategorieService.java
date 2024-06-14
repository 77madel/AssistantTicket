package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CategorieService {

    private CategorieRepository categorieRepository;

    public Iterable<Categorie> getAllCategories() {

        return categorieRepository.findAll();
    }

    public void save(Categorie categorie) {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categorie.setUtilisateur(utilisateur);
        categorieRepository.save(categorie);
    }

    public Optional<Categorie> getCategorieById(long id) {

        return categorieRepository.findById(id);
    }

    public void updateCategorie(Categorie categorie) {

        categorieRepository.save(categorie);
    }

    public void deleteCategorie(long id) {

        categorieRepository.deleteById(id);
    }
}
