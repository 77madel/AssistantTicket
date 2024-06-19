package com.odk.assistantticket.config;

import com.odk.assistantticket.service.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
//La classe JwtFilter hérite de OncePerRequestFilter, garantissant que le filtre est exécuté une seule fois par requête.
public class JwtFilter extends OncePerRequestFilter {

    private UtilisateurService utilisateurService;
    private JwtService jwtService;

    //Le constructeur injecte les dépendances UtilisateurService et JwtService dans la classe JwtFilter.
    public JwtFilter(UtilisateurService utilisateurService, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token  = null; //Contiendra le token JWT extrait de la requête.
        String username = null;// Contiendra le nom d'utilisateur extrait du token.
        boolean isTokenExpired = true; //Indique si le token est expiré.

        //Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

        //request.getHeader("Authorization"); : Récupère l'en-tête Authorization de la requête.
        final String authorization = request.getHeader("Authorization");
        //Vérifie si l'en-tête Authorization est présent et commence par "Bearer".
        if (authorization != null && authorization.startsWith("Bearer")){
            // Extrait le token en enlevant le préfixe "Bearer "
            token = authorization.substring(7);
            //Vérifie si le token est expiré.
            isTokenExpired = jwtService.isTokenExpired(token);
            //Extrait le nom d'utilisateur du token.
            username = jwtService.extractUsername(token);
        }

        // Vérifie que le token n'est pas expiré, que le nom d'utilisateur est extrait, et qu'il n'y a pas déjà une authentification en cours.
        if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Charge les détails de l'utilisateur à partir du service UtilisateurService
            UserDetails userDetails = utilisateurService.loadUserByUsername(username);
            // Crée un jeton d'authentification avec les détails de l'utilisateur et ses autorisations.
            UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //Définit l'authentification dans le contexte de sécurité de Spring.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        //Passe la requête et la réponse au filtre suivant dans la chaîne de filtres.
        filterChain.doFilter(request, response);
    }
}
