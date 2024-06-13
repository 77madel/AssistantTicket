package com.odk.assistantticket.service;


import com.odk.assistantticket.model.Ticket;
import com.odk.assistantticket.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TicketService {

    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    public void insertTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
