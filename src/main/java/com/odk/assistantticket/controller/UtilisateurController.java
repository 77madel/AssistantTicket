package com.odk.assistantticket.controller;

import com.odk.assistantticket.config.JwtService;
import com.odk.assistantticket.dto.AuthentificationDTO;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UtilisateurController(UtilisateurService utilisateurService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/inscription")
    public void inscriptionUtilisateur(@RequestBody Utilisateur utilisateur) {
        this.utilisateurService.inscription(utilisateur);
    }

    @PostMapping(path = "connexion")
    public Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())

        );
        if(authenticate.isAuthenticated()) {
            return this.jwtService.generate(authentificationDTO.username());
        }
        return null;
    }

    @GetMapping("/deconnexion")
    public ResponseEntity<String> deconnexion(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Déconnexion réussie");
    }
}
