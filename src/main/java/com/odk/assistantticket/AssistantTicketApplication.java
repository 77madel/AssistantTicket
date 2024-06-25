package com.odk.assistantticket;

import com.odk.assistantticket.model.Role;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class AssistantTicketApplication {
        private BCryptPasswordEncoder bCryptPasswordEncoder;
        private UtilisateurRepository utilisateurRepository;
    public static void main(String[] args) {
        SpringApplication.run(AssistantTicketApplication.class, args);
    }
}
