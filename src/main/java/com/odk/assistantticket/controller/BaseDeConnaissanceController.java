package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.BaseDeConnaissance;
import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.service.BaseDeConnaissanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("basedeconnaissance")
public class BaseDeConnaissanceController {

    private BaseDeConnaissanceService baseDeConnaissanceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<BaseDeConnaissance> getAllBaseDeConnaissance() {
        return this.baseDeConnaissanceService.listAllBaseDeConnaissance();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<BaseDeConnaissance> BaseDeConnaissanceById(@PathVariable Long id) {
        return this.baseDeConnaissanceService.getBaseDeConnaissanceById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void save(@RequestBody BaseDeConnaissance baseDeConnaissance) {
        this.baseDeConnaissanceService.insertBaseDeConnaissance(baseDeConnaissance);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ResponseEntity<BaseDeConnaissance> updateBase(@PathVariable Long id, @RequestBody BaseDeConnaissance updatedBase) {
        BaseDeConnaissance base = baseDeConnaissanceService.updateBase(id, updatedBase);
        if (base != null) {
            return ResponseEntity.ok(updatedBase);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void supprimerBaseDeConnaissance(@PathVariable Long id) {
        baseDeConnaissanceService.supprimerBase(id);
    }
}
