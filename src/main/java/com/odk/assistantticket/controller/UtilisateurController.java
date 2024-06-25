package com.odk.assistantticket.controller;

import com.odk.assistantticket.dto.AuthentificationDTO;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.service.UtilisateurService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
//@RequestMapping("/api/utilisateurs")
@AllArgsConstructor
@Tag(name = "ADMINISTRATEUR", description = "Api des gestion des Utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/inscription")
    @Operation(summary = "Inscription des utilisateur", description = "Cette fonction permet aux admins de creer des utilisation")

    public ResponseEntity<String> inscriptionUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurService.inscription(utilisateur);
        return ResponseEntity.ok("Utilisateur inscrit avec succès !");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/utilisateur")
    @Operation(summary = "Liste des Utilisateurs", description = "Liste de tous les Utilisateurs")
    public Iterable<Utilisateur> AllUtilisateurs() {
        return utilisateurService.listUtilisateur();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/utilisateur/{id}")
    @Operation(summary = "Liste des Utilisateurs par Id", description = "Liste des Utilisateurs par leur Id")
    public Optional<Utilisateur> utilisateursById(@PathVariable int id) {
        return utilisateurService.utilisateurById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/utilisateur/{id}")
    @Operation(summary = "Fonction pour supprimer un Utilisateur", description = "Supprimer un Utilisateur par son Id")
    public void utilisateurSupprimer(@PathVariable int id) {
        utilisateurService.supprimerUtilisateur(id);
    }

    @PostMapping("/connexion")
    @Operation(summary = "Connexion", description = "Authentication des Utilisations")
    public ResponseEntity<String> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
            );
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return ResponseEntity.ok("Connexion réussie !");
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'authentification");
        }
    }

    @GetMapping("/deconnexion")
    @Operation(summary = "Detruire une Session Utilisateur", description = "Deconnecter Un Utilisateur")
    public ResponseEntity<String> deconnexion(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Déconnexion réussie !");
    }
}
