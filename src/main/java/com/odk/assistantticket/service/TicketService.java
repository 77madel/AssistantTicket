package com.odk.assistantticket.service;


import com.odk.assistantticket.enums.TypeStatus;
import com.odk.assistantticket.model.*;
import com.odk.assistantticket.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TicketService {

    private final JavaMailSenderImpl mailSender;
    private TicketRepository ticketRepository;
    private UtilisateurRepository utilisateurRepository;
    private CategorieRepository categorieRepository;
    private PrioriteRepository prioriteRepository;
    private NotificationService notificationService;
    private ReponseRepository responseRepository;
    private EmailService emailService;

    public List<Ticket> getAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketsById(long id) {
       return this.ticketRepository.findById(id);
    }

    public void insertTicket(Ticket ticket) {
        // Récupère l'utilisateur authentifié
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ticket.setUtilisateur(utilisateur);
        ticket.setStatus(TypeStatus.valueOf("OUVERT"));
        Ticket savedTicket = ticketRepository.save(ticket);

        // Créer une notification pour le ticket
        createNotificationForUser(savedTicket, "Nouveau ticket créé" + " " + utilisateur.getName() + " " + "Veuillez consuler s'il vous plait" );
    }

    private void createNotificationForUser(Ticket ticket, String content) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setDateEnvoie(new Date());
        notification.setTicket(ticket);
        // Récupère l'utilisateur authentifié
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notification.setUtilisateur(utilisateur);
        notificationService.insertNotification(ticket, content);
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

    @Transactional
    public void traiterTicket(Long id, TypeStatus status, String reponseContent) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setStatus(status);
            ticketRepository.save(ticket);

            Reponse reponse = new Reponse();
            reponse.setTicket(ticket);
            reponse.setUtilisateur((Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            reponse.setContent(reponseContent);
            reponse.setDateReponse(new Date());
            responseRepository.save(reponse);

            sendNotificationEmail(ticket.getUtilisateur(), status, reponseContent);
        }
    }

    private void sendNotificationEmail(Utilisateur utilisateur, TypeStatus status, String reponseContent) {
        String subject = "Ticket " + (status == TypeStatus.EN_COURS ? "en cours" : "résolu");
        String body = "Votre ticket a été " + (status == TypeStatus.EN_COURS ? "pris en charge" : "résolu") + ".\n\nRéponse : " + reponseContent;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(utilisateur.getEmail());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}
