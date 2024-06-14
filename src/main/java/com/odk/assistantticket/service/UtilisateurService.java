package com.odk.assistantticket.service;

import com.odk.assistantticket.enums.TypeRole;
import com.odk.assistantticket.model.Role;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Iterable<Utilisateur> listUtilisateur() {
        return utilisateurRepository.findAll();
    }

    public void inscription(Utilisateur utilisateur) {

        if (!utilisateur.getEmail().contains("@")){
            throw new RuntimeException("Votre email est invalide");
        }
        if (!utilisateur.getEmail().contains(".")){
            throw new RuntimeException("Votre email est invalide");
        }

        Optional<Utilisateur> utilisateurOptional = this.utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurOptional.isPresent()){
            throw new RuntimeException("Votre mail est déja utilisé");
        }
        String mdpCrypte = this.bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(mdpCrypte);

        Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(TypeRole.APPRENANT);
        utilisateur.setRole(roleUtilisateur);
        this.utilisateurRepository.save(utilisateur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne corespond a cet identifiant"));
    }
}
