package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Ticket;
import com.odk.assistantticket.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("ticket")
public class TicketController {

    private TicketService ticketService;

    @GetMapping
    public Iterable<Ticket> AllTickets() {
        return this.ticketService.getAllTickets();
    }

    @PostMapping
    public void CreateTicket(@RequestBody Ticket ticket) {
        ticketService.insertTicket(ticket);
    }
}
