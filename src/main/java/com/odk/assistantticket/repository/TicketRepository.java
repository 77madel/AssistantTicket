package com.odk.assistantticket.repository;

import com.odk.assistantticket.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
