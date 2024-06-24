package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Role;
import com.odk.assistantticket.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<Role> allRole(){
        return roleService.getAllRoles();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<Role> RoleById(@PathVariable Long id){
        return roleService.getRoleById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addRole(@RequestBody Role role){
        roleService.save(role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{id}")
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role){
        return roleService.updateRole(id,role);
    }


}
