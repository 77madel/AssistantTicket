package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Notification;
import com.odk.assistantticket.model.Ticket;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.NotificationRepository;
import com.odk.assistantticket.repository.TicketRepository;
import com.odk.assistantticket.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationService {

    private NotificationRepository notificationRepository;
    private TicketRepository ticketRepository;
    private JavaMailSender mailSender;
    private UtilisateurRepository utilisateurRepository;
    private EmailService emailService;

    public Iterable<Notification> listAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(long id) {
        return notificationRepository.findById(id);
    }

    public void insertNotification( Ticket ticket,String content) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setDateEnvoie(new Date());
        notification.setTicket(ticket);
        // Récupère l'utilisateur authentifié
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notification.setUtilisateur(utilisateur);

        notificationRepository.save(notification);
        // Récupérer les utilisateurs avec le rôle "FORM"
        List<Utilisateur> formateurs = utilisateurRepository.findByRoleLibelle("FORMATEUR");

        // Envoyer un e-mail à chaque formateur
        for (Utilisateur formateur : formateurs) {
            emailService.sendSimpleEmail(
                    formateur.getEmail(),
                    "Nouvelle Notification",
                    content
            );
        }
    }

    //Modification Notification
    public Notification updateNotification(Long id, Notification updatedNotification) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("notification non trouvé: " + id));

        // Mettre à jour les champs de la notification existante avec les nouvelles valeurs
        existingNotification.setContent(updatedNotification.getContent());
        existingNotification.setDateEnvoie(new Date());

        // Vérifier et mettre à jour l'utilisateur associé à la catégorie
        if (updatedNotification.getTicket() != null) {
            Ticket ticket = ticketRepository.findById(updatedNotification.getTicket().getId())
                    .orElseThrow(() -> new RuntimeException("Notification non trouvé: " + updatedNotification.getTicket().getId()));
            existingNotification.setTicket(ticket);
        } else {
            existingNotification.setTicket(null);
        }

        // Sauvegarder la catégorie mise à jour et retourner l'entité mise à jour
        return notificationRepository.save(existingNotification);
    }

    public void deleteNotification(long id) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("notification non trouvé: " + id));

        notificationRepository.delete(existingNotification);
    }
}
