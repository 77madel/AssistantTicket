package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Categorie", description = "Géstion des categories")
public class CategorieController {

    private CategorieService categorieService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    @Operation(summary = "Liste des categories", description = "Liste de toute les notifications")
    public Iterable<Categorie> ListAllCategories() {

        return categorieService.getAllCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Créer une categorie", description = "Création des categories")
    public void créerCategorie(@RequestBody Categorie categorie) {
        categorieService.save(categorie);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping({"/{id}"})
    @Operation(summary = "Liste de categorie par ID", description = "Afficher de categorie par son ID")
    public Optional<Categorie> CategorieById(@PathVariable int id) {
        return categorieService.getCategorieById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    @Operation(summary = "Modifier une categorie par ID", description = "Modification des categories par ID")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Integer id, @RequestBody Categorie updatedCategorie) {
        Categorie cat = categorieService.updateCategorie(id, updatedCategorie);
        if (cat != null) {
            return ResponseEntity.ok(updatedCategorie);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping({"/{id}"})
    @Operation(summary = "Supprimer une categorie par ID", description = "Suppression des categories par ID")
    public void supprimerCategorie(@PathVariable int id) {

        this.categorieService.deleteCategorie(id);
    }


}
