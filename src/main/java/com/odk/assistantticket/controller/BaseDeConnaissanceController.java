package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.BaseDeConnaissance;
import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.service.BaseDeConnaissanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("basedeconnaissance")
@Tag(name = "Base De Connaisance", description = "Géstion de Base de connaissance")
public class BaseDeConnaissanceController {

    private BaseDeConnaissanceService baseDeConnaissanceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Liste des bases de connaisance", description = "Liste de toute les bases de connaisance")
    public Iterable<BaseDeConnaissance> getAllBaseDeConnaissance() {
        return this.baseDeConnaissanceService.listAllBaseDeConnaissance();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Liste base de connaisance par ID", description = "Afficher base de connaisance par son ID")
    public Optional<BaseDeConnaissance> BaseDeConnaissanceById(@PathVariable Long id) {
        return this.baseDeConnaissanceService.getBaseDeConnaissanceById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    @Operation(summary = "Créer une base de connaissance", description = "Création des bases de connaissance")
    public void save(@RequestBody BaseDeConnaissance baseDeConnaissance) {
        this.baseDeConnaissanceService.insertBaseDeConnaissance(baseDeConnaissance);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    @Operation(summary = "Modifier une base de connaissance par ID", description = "Modification des bases de connaissance par ID")
    public ResponseEntity<BaseDeConnaissance> updateBase(@PathVariable Long id, @RequestBody BaseDeConnaissance updatedBase) {
        BaseDeConnaissance base = baseDeConnaissanceService.updateBase(id, updatedBase);
        if (base != null) {
            return ResponseEntity.ok(updatedBase);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une base de connaissance par ID", description = "Suppression des  bases de connaissance par ID")
    public void supprimerBaseDeConnaissance(@PathVariable Long id) {
        baseDeConnaissanceService.supprimerBase(id);
    }
}
