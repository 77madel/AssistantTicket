package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Priorite;
import com.odk.assistantticket.service.PriorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("priorite")
public class PrioriteController {

    private PriorityService priorityService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<Priorite> listAllPriorites() {
        return this.priorityService.getAllPriorite();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<Priorite> PrioritesById(@PathVariable long id) {
        return this.priorityService.getPrioriteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void Add(@RequestBody Priorite priorite) {

        priorityService.AddPriorite(priorite);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ResponseEntity<Priorite> updatedPriorite(@PathVariable long id, @RequestBody Priorite priorite) {
        Priorite cat = priorityService.updatedPriorite(id, priorite);
        if (cat != null) {
            return ResponseEntity.ok(priorite);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void supprimerPriorite(@PathVariable long id){
        this.priorityService.deletePriorite(id);
    }

}
