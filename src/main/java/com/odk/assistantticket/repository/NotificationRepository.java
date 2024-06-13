package com.odk.assistantticket.repository;

import com.odk.assistantticket.model.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
