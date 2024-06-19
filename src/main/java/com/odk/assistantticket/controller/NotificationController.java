package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Notification;
import com.odk.assistantticket.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("notification")
public class NotificationController {

    private final NotificationService notificationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<Notification> getAllNotifications() {
        return notificationService.listAllNotifications();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<Notification> getNotificationsById(@PathVariable int id) {
        return notificationService.getNotificationById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void save(@RequestBody Notification notification) {
        notificationService.insertNotification(notification);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        Notification notif = notificationService.updateNotification(id, notification);
        if (notif != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void supprimerNotification(@PathVariable int id) {
        notificationService.deleteNotification(id);
    }
}
