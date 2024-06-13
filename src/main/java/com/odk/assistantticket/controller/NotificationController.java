package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Notification;
import com.odk.assistantticket.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void save(@RequestBody Notification notification) {
        notificationService.insertNotification(notification);
    }
}
