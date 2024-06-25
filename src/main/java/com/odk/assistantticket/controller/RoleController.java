package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Role;
import com.odk.assistantticket.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Tag(name = "Rôle", description = "Géstion des Rôles")
public class RoleController {

    private RoleService roleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Liste des rôles", description = "Liste de tous les rôles")
    public Iterable<Role> allRole(){
        return roleService.getAllRoles();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Liste rôle par Id", description = "Affichage d'un rôle par leur Id")
    public Optional<Role> RoleById(@PathVariable Long id){
        return roleService.getRoleById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Créer un rôle", description = "Création d'un rôle")
    public void addRole(@RequestBody Role role){
        roleService.save(role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Supprimer un rôle", description = "Suppression d'un rôle")
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un rôle", description = "Modification d'un rôle")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role){
        return roleService.updateRole(id,role);
    }


}
