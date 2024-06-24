package com.odk.assistantticket.controller;

import com.odk.assistantticket.enums.TypeStatus;
import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Ticket;
import com.odk.assistantticket.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("ticket")
public class TicketController {

    private TicketService ticketService;

    @GetMapping
    public Iterable<Ticket> AllTickets() {
        return this.ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Optional<Ticket> TicketsById(@PathVariable long id) {
        return ticketService.getTicketsById(id);
    }

    @PostMapping
    public void CreateTicket(@RequestBody Ticket ticket) {
        ticketService.insertTicket(ticket);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateCategorie(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        Ticket ticket = ticketService.updateTicket(id, updatedTicket);
        if (ticket != null) {
            return ResponseEntity.ok(updatedTicket);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable long id) {
        this.ticketService.supprimerTicket(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("traiter/{id}")
    public void traiterTicket(@PathVariable Long id, @RequestParam("status") String status, @RequestParam("reponseContent") String reponseContent) {
        ticketService.traiterTicket(id, status, reponseContent);
    }
}
