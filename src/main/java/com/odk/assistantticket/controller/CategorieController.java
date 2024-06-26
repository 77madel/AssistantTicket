package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("categorie")
public class CategorieController {

    private CategorieService categorieService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public Iterable<Categorie> ListAllCategories() {
        return categorieService.getAllCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creerCategorie(@RequestBody Categorie categorie) {
        categorieService.save(categorie);
    }

//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @GetMapping
//    public Optional<Categorie> CategorieById(long id) {
//        return categorieService.getCategorieById(id);
//    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping
    public void modifierCategorie(@RequestBody Categorie categorie) {
        categorieService.save(categorie);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void supprimerCategorie(long id) {
        categorieService.deleteCategorie(id);
    }

}
