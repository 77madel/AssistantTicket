package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void créerCategorie(@RequestBody Categorie categorie) {
        categorieService.save(categorie);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping({"/{id}"})
    public Optional<Categorie> CategorieById(@PathVariable int id) {
        return categorieService.getCategorieById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Integer id, @RequestBody Categorie updatedCategorie) {
        Categorie cat = categorieService.updateCategorie(id, updatedCategorie);
        if (cat != null) {
            return ResponseEntity.ok(updatedCategorie);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping({"/{id}"})
    public void supprimerCategorie(@PathVariable int id) {

        this.categorieService.deleteCategorie(id);
    }


}
