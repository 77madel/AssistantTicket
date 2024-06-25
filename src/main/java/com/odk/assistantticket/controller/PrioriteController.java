package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Priorite;
import com.odk.assistantticket.service.PriorityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("priorite")
@Tag(name = "Priorité", description = "Géstion des priorités")
public class PrioriteController {

    private PriorityService priorityService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Liste des priorités", description = "Liste de toute les priorités")
    public Iterable<Priorite> listAllPriorites() {
        return this.priorityService.getAllPriorite();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Liste de priorité par ID", description = "Liste de priorité par son ID")
    public Optional<Priorite> PrioritesById(@PathVariable long id) {
        return this.priorityService.getPrioriteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Créer une priorité", description = "Création des priorités")
    public void Add(@RequestBody Priorite priorite) {

        priorityService.AddPriorite(priorite);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    @Operation(summary = "Modifier une priorité par son ID", description = "Modification de priorité par son ID")
    public ResponseEntity<Priorite> updatedPriorite(@PathVariable long id, @RequestBody Priorite priorite) {
        Priorite cat = priorityService.updatedPriorite(id, priorite);
        if (cat != null) {
            return ResponseEntity.ok(priorite);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer de priorité par ID", description = "Suppression de priorité par son ID")
    public void supprimerPriorite(@PathVariable long id){
        this.priorityService.deletePriorite(id);
    }

}
