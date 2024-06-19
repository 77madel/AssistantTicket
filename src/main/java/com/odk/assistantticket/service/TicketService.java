package com.odk.assistantticket.service;


import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Priorite;
import com.odk.assistantticket.model.Ticket;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.CategorieRepository;
import com.odk.assistantticket.repository.PrioriteRepository;
import com.odk.assistantticket.repository.TicketRepository;
import com.odk.assistantticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private UtilisateurRepository utilisateurRepository;
    private CategorieRepository categorieRepository;
    private PrioriteRepository prioriteRepository;

    public List<Ticket> getAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketsById(long id) {
       return this.ticketRepository.findById(id);
    }

    public void insertTicket(Ticket ticket) {
        this.ticketRepository.save(ticket);
    }


    //Modification Categorie
    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé: " + id));

        // Mettre à jour les champs du ticket existant avec les nouvelles valeurs
        existingTicket.setTitle(updatedTicket.getTitle());
        existingTicket.setContent(updatedTicket.getContent());
        existingTicket.setStatus(updatedTicket.getStatus());

        // Vérifier et mettre à jour la catégorie associée au ticket
        if (updatedTicket.getCategorie() != null) {
            Categorie categorie = categorieRepository.findById(updatedTicket.getCategorie().getId())
                    .orElseThrow(() -> new RuntimeException("L'id categorie non trouvé: " + updatedTicket.getCategorie().getId()));
            existingTicket.setCategorie(categorie);
        } else {
            existingTicket.setCategorie(null);
        }

        // Vérifier et mettre à jour la priorité associée au ticket
        if (updatedTicket.getPriorite() != null) {
            Priorite priorite = prioriteRepository.findById(updatedTicket.getPriorite().getId())
                    .orElseThrow(() -> new RuntimeException("L'id priorité non trouvé: " + updatedTicket.getPriorite().getId()));
            existingTicket.setPriorite(priorite);
        } else {
            existingTicket.setPriorite(null);
        }

        // Vérifier et mettre à jour l'utilisateur associé au ticket
        if (updatedTicket.getUtilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(updatedTicket.getUtilisateur().getId())
                    .orElseThrow(() -> new RuntimeException("L'id utilisateur non trouvé: " + updatedTicket.getUtilisateur().getId()));
            existingTicket.setUtilisateur(utilisateur);
        } else {
            existingTicket.setUtilisateur(null);
        }

        // Sauvegarder le ticket mis à jour et retourner l'entité mise à jour
        return ticketRepository.save(existingTicket);
    }

    public void supprimerTicket(long id) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé: " + id));
        ticketRepository.delete(existingTicket);
    }
}