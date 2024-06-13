package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Notification;
import com.odk.assistantticket.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private NotificationRepository notificationRepository;

    public Iterable<Notification> listAllNotifications() {
        return notificationRepository.findAll();
    }

    public void insertNotification(Notification notification) {
        notificationRepository.save(notification);
    }
}
