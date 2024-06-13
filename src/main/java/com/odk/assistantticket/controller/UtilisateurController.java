package com.odk.assistantticket.controller;

import com.odk.assistantticket.dto.AuthentificationDTO;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.service.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {

    private UtilisateurService utilisateurService;
    private AuthenticationManager authenticationManager;
    @PostMapping("/inscription")
    public void inscriptionUtilisateur(@RequestBody Utilisateur utilisateur) {
        log.info("Inscription");
        utilisateurService.inscription(utilisateur);
    }

    @PostMapping(path = "connexion")
    public String connexion(@RequestBody AuthentificationDTO authentificationDTO) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
        );

        return null;
    }
}
