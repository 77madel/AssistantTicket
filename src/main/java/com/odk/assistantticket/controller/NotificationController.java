package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Notification;
import com.odk.assistantticket.model.Ticket;
import com.odk.assistantticket.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("notification")
@Tag(name = "Notification", description = "Géstion des notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Liste des notifications", description = "Liste de toute les notifications")
    public Iterable<Notification> getAllNotifications() {
        return notificationService.listAllNotifications();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Liste de notification par ID", description = "Liste de notification par son ID")
    public Optional<Notification> getNotificationsById(@PathVariable int id) {
        return notificationService.getNotificationById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    @Operation(summary = "créer une notification", description = "Création des notifications")
    public void save(@RequestBody Notification notification, Ticket ticket, String content) {
        notificationService.insertNotification(ticket, content);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    @Operation(summary = "Modifier une notification par ID", description = "Modification des notifications par ID")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        Notification notif = notificationService.updateNotification(id, notification);
        if (notif != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une notification par ID", description = "Suppression des notifications par ID")
    public void supprimerNotification(@PathVariable int id) {
        notificationService.deleteNotification(id);
    }
}
